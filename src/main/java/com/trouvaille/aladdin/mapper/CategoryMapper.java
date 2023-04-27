package com.trouvaille.aladdin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trouvaille.aladdin.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表(Category)表数据库访问层
 *
 * @author trouvaille
 * @since 2023-04-27 21:30:37
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

