package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Category;
import com.trouvaille.aladdin.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 商品分类表(Category)表控制层
 *
 * @author trouvaille
 * @since 2023-04-27 21:42:27
 */
@RestController
@Slf4j
@RequestMapping ("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    
    @GetMapping ("/list")
    public R<List<Category>> list () {
        
        List<Category> category = this.categoryService.list(null);
        
        String redisKey = "Category:list";
        if (this.redisTemplate.hasKey(redisKey)) {
            return R.success((List<Category>) this.redisTemplate.opsForValue().get(redisKey));
        }
        
        log.info("Category--list===>{}" , category);
        
        
        this.redisTemplate.opsForValue().set(redisKey , category , 60L , TimeUnit.MINUTES);
        
        return R.success(category);
    }
    
    @GetMapping ("/page")
    public R<Page<Category>> page (int page , int pageSize) {
        log.info("Category--page:page, pageSize==>{},{}" , page , pageSize);
        
        String redisKey = "Category:page:" + page + ":" + pageSize;
        if (this.redisTemplate.hasKey(redisKey)) {
            return R.success((Page<Category>) this.redisTemplate.opsForValue().get(redisKey));
        }
        
        Page<Category> pageInfo = new Page<>(page , pageSize);
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Category::getSort);
        this.categoryService.page(pageInfo , lqw);
        
        
        this.redisTemplate.opsForValue().set(redisKey , pageInfo , 60L , TimeUnit.MINUTES);
        
        return R.success(pageInfo);
    }
    
    
    @GetMapping ("/{id}")
    public R<Category> getById (@PathVariable Long id) {
        log.info("Category--getById:id==>{}" , id);
        String redisKey = "Category:getById:" + id;
        if (this.redisTemplate.hasKey(redisKey)) {
            return R.success((Category) this.redisTemplate.opsForValue().get(redisKey));
        }
        Category category = this.categoryService.getById(id);
        this.redisTemplate.opsForValue().set(redisKey , category , 60L , TimeUnit.MINUTES);
        
        return R.success(category);
    }
    
    
    @PostMapping ("/save")
    public R<String> save (@RequestBody Category category) {
        log.info("Category--save: category==>{}" , category);
        boolean save = this.categoryService.save(category);
        Set<String> redisKey = this.redisTemplate.keys("Category" + "*");
        this.redisTemplate.delete(redisKey);
        return R.flag(save , "新增信息成功!" , "新增信息失败,请重试!");
    }
    
    
    @PutMapping ("/update")
    public R<String> update (@RequestBody Category category) {
        log.info("Category--update: category==>{}" , category);
        boolean update = this.categoryService.updateById(category);
        Set<String> redisKey = this.redisTemplate.keys("Category" + "*");
        this.redisTemplate.delete(redisKey);
        return R.flag(update , "更改信息成功!" , "更改信息失败,请重试!");
    }
    
    
    @DeleteMapping ("/{id}")
    public R<String> delete (@PathVariable Long id) {
        log.info("Category--delete: id==>{}" , id);
        boolean delete = this.categoryService.removeById(id);
        Set<String> redisKey = this.redisTemplate.keys("Category" + "*");
        this.redisTemplate.delete(redisKey);
        return R.flag(delete , "删除信息成功!" , "删除信息失败,请重试!");
        
    }
}

