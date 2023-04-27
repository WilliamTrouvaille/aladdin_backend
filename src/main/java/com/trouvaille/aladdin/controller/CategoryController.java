package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Category;
import com.trouvaille.aladdin.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类表(Category)表控制层
 *
 * @author trouvaille
 * @since 2023-04-27 21:42:27
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public R<List<Category>> list() {

        List<Category> category = categoryService.list(null);
        log.info("Category--list===>{}", category);
        return R.success(category);
    }

    @GetMapping("/page")
    public R<Page<Category>> page(int page, int pageSize) {
        log.info("Category--page:page, pageSize==>{},{}", page, pageSize);
        Page<Category> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Category::getUpdateTime);
        categoryService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }


    @GetMapping("/{id}")
    public R<Category> getById(@PathVariable Long id) {
        log.info("Category--getById:id==>{}", id);
        Category category = categoryService.getById(id);
        return R.success(category);
    }


    @PostMapping("/save")
    public R<String> save(@RequestBody Category category) {
        log.info("Category--save: category==>{}", category);
        boolean save = categoryService.save(category);
        return R.flag(save, "新增信息成功!", "新增信息失败,请重试!");
    }


    @PutMapping("/update")
    public R<String> update(@RequestBody Category category) {
        log.info("Category--update: category==>{}", category);
        boolean update = categoryService.updateById(category);
        return R.flag(update, "更改信息成功!", "更改信息失败,请重试!");
    }


    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        log.info("Category--delete: id==>{}", id);
        boolean delete = categoryService.removeById(id);
        return R.flag(delete, "删除信息成功!", "删除信息失败,请重试!");

    }
}

