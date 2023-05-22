package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * @ClassName SalesController
 * @Description 订单控制器
 * @Author willi
 * @Date 2021/4/22
 * @Version 1.0
 */
@Data
@RestController
@Slf4j
@RequestMapping ("/sales")
public class SalesController {
    
    @Autowired
    private SalesService salesService;
    
    @Autowired
    private SalesDetailService salesDetailService;
    
    
    @Autowired
    private CommodityService commodityService;
    
    
    @Autowired
    private RedisTemplate redisTemplate;


/**
 * @param sales:
 * @return R < List  <  SalesDto>>
 * @description 查询全部销售记录信息
 * @date 2023/04/22 16:39
 */

//    @GetMapping("/list")
//    public R < List < SalesDto>> list(Sales sales) {
//        LambdaQueryWrapper < Sales> queryWrapper = new LambdaQueryWrapper < >();
//
//        queryWrapper.orderByDesc(Sales::getCheckoutTime);
//
//        List < Sales> salesList = salesService.list(queryWrapper);
//
//        List < SalesDto> list = salesList.stream().map((item) -> {
//
//            SalesDto salesDto = new SalesDto();
//            BeanUtils.copyProperties(item, salesDto);
////            salesDto.setCommodityName(commodityService.getById(item.getCommodity()).getName());
//            return salesDto;
//        }).collect(Collectors.toList());
//
//        return R.success(list);
//    }
    
    
    /**
     * @param page:
     * @param pageSize:
     * @param beginTime:
     * @param endTime:
     * @return R < Page < SalesDto>>
     * @author willi
     * @description 管理端查询销售记录
     * @since 2023/05/05 12:05
     */
    @GetMapping ("/page")
    public R<Page<SalesDto>> page (int page , int pageSize , String beginTime , String endTime) {
        log.info("Sales - Page:id, page, pageSize,beginTime,endTime==>{},{},{}" , page ,
                pageSize , beginTime , endTime);
        
        String redisKey = "Sales:page:" + page + ":" + pageSize + ":" + beginTime + ":" + endTime;
        if (this.redisTemplate.hasKey(redisKey)) {
            List<SalesDto> salesDtos = (List<SalesDto>) this.redisTemplate.opsForValue().get(redisKey);
            return R.success(new Page<SalesDto>(page , pageSize , salesDtos.size()).setRecords(salesDtos));
        }
        
        
        Page<Sales> pageInfo = new Page<>(page , pageSize);
        Page<SalesDto> pageDtoInfo = new Page<>(page , pageSize);
        
        LambdaQueryWrapper<Sales> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Sales::getCheckoutTime);
        lqw.ge(beginTime != null , Sales::getCheckoutTime , beginTime);
        lqw.le(endTime != null , Sales::getCheckoutTime , endTime);
        
        R<Page<SalesDto>> pageR = this.pageR(lqw , pageInfo , pageDtoInfo);
        
        
        this.redisTemplate.opsForValue().set(redisKey , pageR.getData().getRecords() , 60L , TimeUnit.MINUTES);
        
        
        return pageR;
    }
    
    /**
     * @param sales:
     * @return R  <  String>
     * @author willi
     * @description 提交订单
     * @since 2023/05/01 23:17
     */
    @PostMapping ("/submit")
    public R<String> submit (@RequestBody Sales sales) {
        log.info("提交--订单数据：{}" , sales);
        
        Set<String> redisKey = this.redisTemplate.keys("Sales" + "*");
        
        boolean flag = this.salesService.submit(sales);
        
        this.redisTemplate.delete(redisKey);
        
        return R.flag(flag);
    }
    
    @GetMapping ("/again/{salesId}")
    public R<String> again (@PathVariable Long salesId) {
        log.info("再来一单--订单Id：{}" , salesId);
        
        Set<String> redisKey = this.redisTemplate.keys("Sales" + "*");
        
        boolean flag = this.salesService.again(salesId);
        
        this.redisTemplate.delete(redisKey);
        return R.flag(flag);
    }
    
    @GetMapping ("/cancel/{salesId}")
    public R<String> cancel (@PathVariable Long salesId) {
        log.info("订单取消--订单Id：{}" , salesId);
        
        Sales sales = new Sales();
        sales.setStatus(5);
        
        QueryWrapper<Sales> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id" , salesId);
        
        boolean flag = this.salesService.update(sales , queryWrapper);

//        String redisKey = "Sales*";
        Set<String> redisKey = this.redisTemplate.keys("Sales" + "*");
        
        this.redisTemplate.delete(redisKey);
        return R.flag(flag);
    }
    
    @GetMapping ("/distribute/{salesId}")
    public R<String> distribute (@PathVariable Long salesId) {
        log.info("订单配送--订单Id：{}" , salesId);
        
        Sales sales = new Sales();
        sales.setStatus(3);
        
        QueryWrapper<Sales> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id" , salesId);
        
        boolean flag = this.salesService.update(sales , queryWrapper);

//        String redisKey = "Sales*";
        Set<String> redisKey = this.redisTemplate.keys("Sales" + "*");
        
        this.redisTemplate.delete(redisKey);
        return R.flag(flag);
    }
    
    @GetMapping ("/confirm/{salesId}")
    public R<String> confirm (@PathVariable Long salesId) {
        log.info("订单确认--订单Id：{}" , salesId);
        
        Sales sales = new Sales();
        sales.setStatus(4);
        
        QueryWrapper<Sales> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id" , salesId);
        
        boolean flag = this.salesService.update(sales , queryWrapper);

//        String redisKey = "Sales*";
        Set<String> redisKey = this.redisTemplate.keys("Sales" + "*");
        
        this.redisTemplate.delete(redisKey);
        return R.flag(flag);
    }
    
    @GetMapping ("/userPage")
    public R<Page<SalesDto>> userPage (int page , int pageSize) {
        log.info("Sales - userPage: page, pageSize==>{},{}" , page , pageSize);

//        获取当前用户id
        Long userId = BaseContext.getCurrentId();
        
        String redisKey = "Sales:userPage:userId:" + userId + ":" + page + ":" + pageSize;
        if (this.redisTemplate.hasKey(redisKey)) {
            List<SalesDto> salesDtos = (List<SalesDto>) this.redisTemplate.opsForValue().get(redisKey);
            return R.success(new Page<SalesDto>(page , pageSize , salesDtos.size()).setRecords(salesDtos));
        }

//        最终返回的分页对象
        Page<SalesDto> pageDtoInfo = new Page<>(page , pageSize);
        Page<Sales> pageInfo = new Page<>(page , pageSize);
        
        LambdaQueryWrapper<Sales> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Sales::getCheckoutTime);
        lqw.eq(Objects.nonNull(userId) , Sales::getUserId , userId);
        
        R<Page<SalesDto>> pageR = this.pageR(lqw , pageInfo , pageDtoInfo);
        
        
        this.redisTemplate.opsForValue().set(redisKey , pageR.getData().getRecords() , 60L , TimeUnit.MINUTES);
        
        
        return pageR;
        
    }
    
    public R<Page<SalesDto>> pageR (LambdaQueryWrapper lqw , Page<Sales> pageInfo , Page<SalesDto> pageDtoInfo) {

//        分页查询
        this.salesService.page(pageInfo , lqw);

//        拷贝分页信息
        BeanUtils.copyProperties(pageInfo , pageDtoInfo , "records");

//        获取分页信息
        List<Sales> records = pageInfo.getRecords();

//        转换为dto
        List<SalesDto> dtoRecords = records.stream().map((item) -> {
            SalesDto salesDto = new SalesDto();
            AtomicReference<Integer> sumNum = new AtomicReference<>(0);
            
            BeanUtils.copyProperties(item , salesDto);
            Long salesId = item.getId();
            LambdaQueryWrapper<SalesDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Objects.nonNull(salesId) , SalesDetail::getSalesId , salesId);
            
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

