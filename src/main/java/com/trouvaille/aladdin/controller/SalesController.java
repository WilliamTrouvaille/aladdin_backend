package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.BaseContext;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Commodity;
import com.trouvaille.aladdin.entity.Sales;
import com.trouvaille.aladdin.entity.SalesDetail;
import com.trouvaille.aladdin.entity.dto.SalesDto;
import com.trouvaille.aladdin.service.CommodityService;
import com.trouvaille.aladdin.service.SalesDetailService;
import com.trouvaille.aladdin.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * @ClassName SalesController
 * @Description 订单控制器
 * @Author willi
 * @Date 2021/4/22
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesDetailService salesDetailService;


    @Autowired
    private CommodityService commodityService;


    /**
     * @param sales:
     * @return R<List < SalesDto>>
     * @description 查询全部销售记录信息
     * @date 2023/04/22 16:39
     */

    @GetMapping("/list")
    public R<List<SalesDto>> list(Sales sales) {
        LambdaQueryWrapper<Sales> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByDesc(Sales::getCheckoutTime);

        List<Sales> salesList = salesService.list(queryWrapper);

        List<SalesDto> list = salesList.stream().map((item) -> {

            SalesDto salesDto = new SalesDto();
            BeanUtils.copyProperties(item, salesDto);
//            salesDto.setCommodityName(commodityService.getById(item.getCommodity()).getName());
            return salesDto;
        }).collect(Collectors.toList());

        return R.success(list);
    }


    /**
     * @param page:
     * @param pageSize:
     * @param id:
     * @param beginTime:
     * @param endTime:
     * @return R<Page < SalesDto>>
     * @description 分页查询
     * @author willi
     * @date 2023/04/22 16:39
     */
    @GetMapping("/page")
    public R<Page<SalesDto>> page(int page, int pageSize, Long id, String beginTime,
                                  String endTime) {
        log.info("Sales - Page:id, page, pageSize,beginTime,endTime==>{},{},{}", id, page,
                pageSize, beginTime, endTime);
        Page<Sales> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Sales> lqw = new LambdaQueryWrapper<>();
        lqw.like(id != null, Sales::getId, id);
        lqw.orderByDesc(Sales::getCheckoutTime);
        lqw.ge(beginTime != null, Sales::getCheckoutTime, beginTime);
        lqw.le(endTime != null, Sales::getCheckoutTime, endTime);

//        分组查询
        this.salesService.page(pageInfo, lqw);

//        最终返回对象
        Page<SalesDto> pages = new Page<>(page, pageSize);

//        拷贝分页信息
        BeanUtils.copyProperties(pageInfo, page, "records");

//        获取分页信息
        List<Sales> records = pageInfo.getRecords();

//        List<SalesDto> list = records.stream().map((item) -> {
//            SalesDto salesDto = new SalesDto();
//            BeanUtils.copyProperties(item, salesDto);
//            Long salesId = item.getId();
//
//            LambdaQueryWrapper<SalesDto> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(SalesDto::getId, salesId);
//            List<SalesDto> salesDtoList = this.salesService.list()
//            Commodity commodity = commodityService.getById(commodityId);
//            String commodityName = commodity.getName();
//            salesDto.setCommodityName(commodityName);
////            salesDto.setCommodityName(commodityService.getById(item.getCommodity()).getName());
//            return salesDto;
//        }).collect(Collectors.toList());
//        pages.setRecords(list);
//        log.info(String.valueOf(list));

        return R.success(pages);
    }

    /**
     * @param sales:
     * @return R<String>
     * @author willi
     * @description 提交订单
     * @since 2023/05/01 23:17
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Sales sales) {
        log.info("订单数据：{}", sales);
        boolean flag = this.salesService.submit(sales);
        return R.flag(flag);
    }

    //    TODO
    @GetMapping("/userPage")
    public R<Page<SalesDto>> userPage(int page, int pageSize) {
        log.info("Sales - userPage: page, pageSize==>{},{}", page, pageSize);

//        获取当前用户id
        Long userId = BaseContext.getCurrentId();

//        最终返回的分页对象
        Page<SalesDto> pageDtoInfo = new Page<>(page, pageSize);
        Page<Sales> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Sales> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Sales::getCheckoutTime);
        lqw.eq(Objects.nonNull(userId), Sales::getUserId, userId);

//        分页查询
        this.salesService.page(pageInfo, lqw);

//        拷贝分页信息
        BeanUtils.copyProperties(pageInfo, pageDtoInfo, "records");

//        获取分页信息
        List<Sales> records = pageInfo.getRecords();

//        转换为dto
        List<SalesDto> dtoRecords = records.stream().map((item) -> {
            SalesDto salesDto = new SalesDto();
            AtomicReference<Integer> sumNum = new AtomicReference<>(0);

            BeanUtils.copyProperties(item, salesDto);
            Long salesId = item.getId();
            LambdaQueryWrapper<SalesDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Objects.nonNull(salesId), SalesDetail::getSalesId, salesId);

            List<SalesDetail> salesDetailList = this.salesDetailService.list(lambdaQueryWrapper);
            List<Commodity> commodities = salesDetailList.stream().map((salesDetail -> {
                Commodity commodity = this.commodityService.getById(salesDetail.getCommodityId());
                commodity.setNumber(salesDetail.getNumber());
                sumNum.updateAndGet(v -> v + salesDetail.getNumber());
                return commodity;
            })).collect(Collectors.toList());
            salesDto.setCommodityList(commodities);
            salesDto.setSumNum(sumNum.get());

            return salesDto;

        }).collect(Collectors.toList());

        pageDtoInfo.setRecords(dtoRecords);


        return R.success(pageDtoInfo);
    }


}

