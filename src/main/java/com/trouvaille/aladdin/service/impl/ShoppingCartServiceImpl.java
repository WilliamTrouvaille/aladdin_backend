package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.ShoppingCart;
import com.trouvaille.aladdin.mapper.ShoppingCartMapper;
import com.trouvaille.aladdin.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author trouvaille
 * @since 2022-07-30 12:09:13
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}

