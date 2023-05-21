package com.trouvaille.aladdin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trouvaille.aladdin.entity.ShoppingCart;

/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author trouvaille
 * @since 2022-07-30 12:05:18
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    
    ShoppingCart add (ShoppingCart shoppingCart);
    
    
}

