package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.common.BaseContext;
import com.trouvaille.aladdin.entity.ShoppingCart;
import com.trouvaille.aladdin.mapper.ShoppingCartMapper;
import com.trouvaille.aladdin.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author trouvaille
 * @since 2022-07-30 12:09:13
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    
    @Override
    public ShoppingCart add (ShoppingCart shoppingCart) {
        
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getUserId , shoppingCart.getUserId());
        lqw.eq(ShoppingCart::getCommodityId , shoppingCart.getCommodityId());
        
        ShoppingCart cartServiceOne = this.getOne(lqw);
        
        if (cartServiceOne != null) {
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            this.updateById(cartServiceOne);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.save(shoppingCart);
            
            cartServiceOne = shoppingCart;
        }
        return cartServiceOne;
    }
    
    
}

