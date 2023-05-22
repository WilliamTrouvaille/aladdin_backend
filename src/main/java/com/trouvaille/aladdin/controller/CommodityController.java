/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: CommodityController
 * @author: trouvaille_william
 * @description: Commodity控制类
 * @date: 2022/7/27 15:45
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Commodity;
import com.trouvaille.aladdin.entity.dto.CommodityDto;
import com.trouvaille.aladdin.service.CategoryService;
import com.trouvaille.aladdin.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping ("/commodity")
public class CommodityController {
    
    @Autowired
    private CommodityService commodityService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    
    /**
     * @param page:
     * @param pageSize:
     * @param name:
     * @return R<Page < CommodityDto>>
     * @author willi
     * @description 分页查询商品
     * @since 2023/05/05 11:25
     */
    @GetMapping ("/page")
    public R<Page<CommodityDto>> page (final int page , final int pageSize , final String name) {
        CommodityController.log.info("商品分页查询==>当前页数:{},页面大小:{},查询条件:{}" , page , pageSize , name);
        
        String redisKey = "Commodity:page:" + page + ":" + pageSize + ":" + name;
        if (this.redisTemplate.hasKey(redisKey)) {
            return R.success((Page<CommodityDto>) this.redisTemplate.opsForValue().get(redisKey));
        }
        
        final Page<Commodity> pageInfo = new Page<>(page , pageSize);
        final Page<CommodityDto> pageDtoInfo = new Page<>(page , pageSize);
        
        final LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null , Commodity::getName , name);
        lqw.orderByDesc(Commodity::getUpdateTime);
        
        this.commodityService.page(pageInfo , lqw);
        BeanUtils.copyProperties(pageInfo , pageDtoInfo , new String[]{"records"});
        
        List<CommodityDto> records = pageInfo.getRecords().stream().map((item) -> {
            CommodityDto commodityDto = new CommodityDto();
            commodityDto.setCategoryName(this.categoryService.getById(item.getCategoryId()).getName());
            String newName = item.getName() + '(' + item.getSpecification() + ')';
            item.setName(newName);
            BeanUtils.copyProperties(item , commodityDto);
            return commodityDto;
        }).collect(Collectors.toList());
        
        pageDtoInfo.setRecords(records);
        
        this.redisTemplate.opsForValue().set(redisKey , pageDtoInfo , 60L , TimeUnit.MINUTES);
        
        return R.success(pageDtoInfo);
    }
    
    /**
     * @param categoryId:
     * @return R<List < Commodity>>
     * @author willi
     * @description 根据分类id查询商品
     * @since 2023/05/05 11:52
     */
    @GetMapping ("/category/{categoryId}")
    public R<List<Commodity>> getByCategoryId (@PathVariable Long categoryId) {
        log.info("根据分类id查询商品:{}" , categoryId);
        List<Commodity> commodityList = this.commodityService.list(new LambdaQueryWrapper<Commodity>().eq(Commodity::getCategoryId , categoryId));
        
        List<Commodity> commodities = commodityList.stream().map((item) -> {
            item.setNumber(0);
            return item;
        }).collect(Collectors.toList());
        return R.success(commodities);
    }
    
    @PostMapping
    public R<String> save (@RequestBody Commodity commodity) {
        log.info("新增商品:{}" , commodity);
        boolean save = this.commodityService.save(commodity);
        
        Set<String> redisKey = this.redisTemplate.keys("Commodity" + "*");
        this.redisTemplate.delete(redisKey);
        
        return R.flag(save);
    }
    
    @GetMapping ("/{id}")
    public R<Commodity> getById (@PathVariable Long id) {
        log.info("查询id为{}的商品" , id);
        Commodity commodity = this.commodityService.getById(id);
        return R.success(commodity);
    }
    
    @PutMapping
    public R<String> update (@RequestBody Commodity commodity) {
        log.info("更改商品信息,商品信息==>{}" , commodity.toString());
        boolean flag = this.commodityService.updateById(commodity);
        
        String redisKey = "commodity*";
        this.redisTemplate.delete(redisKey);
        
        return R.flag(flag);
        
    }
    
    @PostMapping ("/status/{status}")
    public R<String> status (@RequestParam List<Long> ids , @PathVariable int status) {
        // 0 更改为停售 1更改为起售
        log.info("更改售卖状态:ids==>{},status==>{}" , ids , status);
        boolean flag = this.commodityService.updateStatus(ids , status);
        
        Set<String> redisKey = this.redisTemplate.keys("Commodity" + "*");
        this.redisTemplate.delete(redisKey);
        
        return flag ? R.success("商品状态已经更改成功！") : R.error("商品状态更改失败,请重试!");
        
    }
    
    @DeleteMapping
    public R<String> delete (@RequestParam List<Long> ids) {
        boolean flag = this.commodityService.removeByIds(ids);
        
        Set<String> redisKey = this.redisTemplate.keys("Commodity" + "*");
        this.redisTemplate.delete(redisKey);
        
        return flag ? R.success("删除成功!") : R.error("删除失败,请重试!");
        
    }
    
}
