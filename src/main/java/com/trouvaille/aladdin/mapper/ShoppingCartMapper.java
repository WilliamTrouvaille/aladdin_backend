package com.trouvaille.aladdin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author trouvaille
 * @since 2022-07-30 12:09:02
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}

