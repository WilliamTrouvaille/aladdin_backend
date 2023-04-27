package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Sales;
import com.trouvaille.aladdin.entity.dto.SalesDto;
import com.trouvaille.aladdin.service.CommodityService;
import com.trouvaille.aladdin.service.SalesDetailService;
import com.trouvaille.aladdin.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

        queryWrapper.orderByDesc(Sales::getCreateTime);

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
        lqw.orderByDesc(Sales::getCreateTime);
        lqw.ge(beginTime != null, Sales::getCreateTime, beginTime);
        lqw.le(endTime != null, Sales::getCreateTime, endTime);
        salesService.page(pageInfo, lqw);
        Page<SalesDto> pages = new Page<>(page, pageSize);
        BeanUtils.copyProperties(pageInfo, page, "records");
        List<Sales> records = pageInfo.getRecords();
//        List<SalesDto> list = records.stream().map((item) -> {
//            SalesDto salesDto = new SalesDto();
//            BeanUtils.copyProperties(item, salesDto);
//            Long commodityId = item.getCommodity();
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


}

