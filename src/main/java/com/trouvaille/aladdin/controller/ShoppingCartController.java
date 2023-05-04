package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.trouvaille.aladdin.common.BaseContext;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.ShoppingCart;
import com.trouvaille.aladdin.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @projectName: F:/Study/homework/java/aladdin/aladdin
 * @package: F:/Study/homework/java/aladdin/aladdin
 * @className: ShoppingCartControllerController
 * @author: trouvaille
 * @description: ShoppingCartController控制类
 * @date: 2022-07-30 12:50:38
 * @version: 1.0
 */

@RestController
@Slf4j
@RequestMapping ("/shoppingCart")
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    
    /**
     * @param :
     * @return R < List  <  ShoppingCart>>
     * @author willi
     * @description 查看购物车
     * @date 2023/04/24 20:04
     */
    @GetMapping ("/list")
    public R<List<ShoppingCart>> list () {
        log.info("查看购物车");
        Long userid = BaseContext.getCurrentId();
        
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getUserId , userid);
        lqw.orderByAsc(ShoppingCart::getCreateTime);
        
        List<ShoppingCart> shoppingCarts = this.shoppingCartService.list(lqw);
        
        
        return R.success(shoppingCarts);
    }
    
    /**
     * @param :
     * @return R  <  String>
     * @author willi
     * @description 清空购物车
     * @date 2023/04/24 20:04
     */
    @DeleteMapping ("/clean")
    public R<String> clean () {
        
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        Long userid = BaseContext.getCurrentId();
        
        lqw.eq(ShoppingCart::getUserId , userid);
        boolean flag = this.shoppingCartService.remove(lqw);
        
        return R.flag(flag);
    }
    
    @PostMapping ("/add")
    public R<ShoppingCart> add (@RequestBody ShoppingCart shoppingCart) {
        log.info("添加购物车信息==.{}" , shoppingCart.toString());
        
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getUserId , shoppingCart.getUserId());
        lqw.eq(ShoppingCart::getCommodityId , shoppingCart.getCommodityId());
        
        ShoppingCart cartServiceOne = this.shoppingCartService.getOne(lqw);
        
        if (cartServiceOne != null) {
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            this.shoppingCartService.updateById(cartServiceOne);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            this.shoppingCartService.save(shoppingCart);
            
            cartServiceOne = shoppingCart;
        }
        return R.success(cartServiceOne);
    }
    
    //    TODO
    @PostMapping ("/sub")
    public R<ShoppingCart> sub (@RequestBody ShoppingCart shoppingCart) {
        log.info("减少购物车商品信息==.{}" , shoppingCart.toString());
        
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        
        LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShoppingCart::getUserId , userId);
        lqw.eq(ShoppingCart::getCommodityId , shoppingCart.getCommodityId());
        
        ShoppingCart cartServiceOne = this.shoppingCartService.getOne(lqw);
        
        Integer number = cartServiceOne.getNumber();
        
        cartServiceOne.setNumber(number - 1);
        this.shoppingCartService.removeById(cartServiceOne);
        
        
        return R.success(cartServiceOne);
        
    }
    
    @GetMapping ("/{id}")
    public R<ShoppingCart> getById (@PathVariable Long id) {
        log.info("ShoppingCart - getById:id==>{}" , id);
        ShoppingCart shoppingCart = this.shoppingCartService.getById(id);
        return R.success(shoppingCart);
    }
    
    
}

