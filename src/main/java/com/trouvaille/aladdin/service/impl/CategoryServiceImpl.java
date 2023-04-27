package com.trouvaille.aladdin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Category;
import com.trouvaille.aladdin.mapper.CategoryMapper;
import com.trouvaille.aladdin.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 商品分类表(Category)表服务实现类
 *
 * @author trouvaille
 * @since 2023-04-27 21:38:30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

