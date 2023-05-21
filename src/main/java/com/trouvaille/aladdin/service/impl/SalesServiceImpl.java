package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.common.BaseContext;
import com.trouvaille.aladdin.entity.*;
import com.trouvaille.aladdin.exception.CustomException;
import com.trouvaille.aladdin.mapper.SalesMapper;
import com.trouvaille.aladdin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @projectName: F:/Study/homework/java/aladdin/aladdin
 * @package: F:/Study/homework/java/aladdin/aladdin
 * @className: SalesServiceImplController
 * @author: trouvaille
 * @description: SalesServiceImpl服务实现类
 * @date: 2022-08-05 21:14:33
 * @version: 1.0
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private SalesDetailService saleDetailService;
    @Autowired
    private CommodityService commodityService;
    
    @Override
    public boolean submit (Sales sales) {
//        设置成功标志
        boolean flag = true;

//        获取当前用户id
        Long userid = BaseContext.getCurrentId();

//        获取当前用户购物车信息
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper();
        lqw.eq(ShoppingCart::getUserId , userid);
        List<ShoppingCart> shoppingCarts = this.shoppingCartService.list(lqw);

//        判断购物车是否为空
        if (shoppingCarts != null && shoppingCarts.size() != 0) {

//            设置订单信息
            AtomicInteger amount = new AtomicInteger(0);
            User user = (User) this.userService.getById(userid);
            Long addressId = sales.getAddressId();
            Address address = (Address) this.addressService.getById(addressId);
            List<Commodity> commodities = new ArrayList<>();
            if (address == null) {
                throw new CustomException("地址信息有误,不能下单!");
            } else {
                long salesId = IdWorker.getId();
                List<SalesDetail> salesDetails = (List) shoppingCarts.stream().map((item) -> {
                    SalesDetail salesDetail = new SalesDetail();
                    Commodity commodity = this.commodityService.getById(item.getCommodityId());
                    commodity.setNumber(commodity.getNumber() - item.getNumber());
                    commodity.setSaleNum(commodity.getSaleNum() + 1L);
                    salesDetail.setSalesId(salesId);
                    salesDetail.setNumber(item.getNumber());
                    salesDetail.setCommodityId(item.getCommodityId());
                    salesDetail.setName(item.getName());
                    salesDetail.setImage(item.getImage());
                    salesDetail.setAmount(item.getAmount());
                    amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
                    commodities.add(commodity);
                    return salesDetail;
                }).collect(Collectors.toList());
                sales.setId(salesId);
                sales.setOrderTime(LocalDateTime.now());
                sales.setCheckoutTime(LocalDateTime.now());
                sales.setStatus(2);
                sales.setAmount(new BigDecimal(amount.get()));
                sales.setUserId(userid);
                sales.setNumber(String.valueOf(salesId));
                sales.setUserName(user.getName());
                sales.setConsignee(address.getConsignee());
                sales.setPhone(address.getPhone());
                sales.setAddress((address.getProvinceName() == null ? "" : address.getProvinceName()) + (address.getCityName() == null ? "" : address.getCityName()) + (address.getDistrictName() == null ? "" : address.getDistrictName()) + (address.getDetail() == null ? "" : address.getDetail()));
                boolean save = this.save(sales);
                boolean saveBatch = this.saleDetailService.saveBatch(salesDetails);
                this.shoppingCartService.remove(lqw);
                return flag && save && saveBatch;
            }
        } else {
            throw new CustomException("购物车为空,不能下单!");
        }
        
        
    }
    
    @Override
    public boolean again (Long salesId) {
        LambdaQueryWrapper<SalesDetail> lqw = new LambdaQueryWrapper();
        lqw.eq(SalesDetail::getSalesId , salesId);
        List<SalesDetail> salesDetails = this.saleDetailService.list(lqw);
        List<ShoppingCart> shoppingCarts = (List) salesDetails.stream().map((item) -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setName(item.getName());
            shoppingCart.setImage(item.getImage());
            shoppingCart.setAmount(item.getAmount());
            shoppingCart.setCommodityId(item.getCommodityId());
            shoppingCart.setId(IdWorker.getId());
            shoppingCart.setNumber(item.getNumber());
            shoppingCart.setUserId(BaseContext.getCurrentId());
            
            
            return shoppingCart;
        }).collect(Collectors.toList());
        this.shoppingCartService.saveBatch(shoppingCarts);
        boolean submit = this.submit((Sales) this.getById(salesId));
        
        return submit;
    }
}

