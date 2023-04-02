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
import com.trouvaille.aladdin.service.CommodityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/page")
    public R<Page<Commodity>> page(final int page, final int pageSize, final String name) {
        CommodityController.log.info("商品分页查询==>当前页数:{},页面大小:{},查询条件:{}", page, pageSize, name);

        final Page<Commodity> pageInfo = new Page<>(page, pageSize);

        final LambdaQueryWrapper<Commodity> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Commodity::getName, name);
        lqw.orderByDesc(Commodity::getUpdateTime);
        commodityService.page(pageInfo, lqw);

        List<Commodity> records = pageInfo.getRecords().stream().map((item) -> {
            String newName = item.getName() + '(' + item.getSpecification() + ')';
            item.setName(newName);
            return item;
        }).collect(Collectors.toList());

        pageInfo.setRecords(records);

        return R.success(pageInfo);
    }

    @PostMapping
    public R<String> save(@RequestBody Commodity commodity) {
        log.info("新增商品:{}", commodity);
        boolean save = commodityService.save(commodity);
        return R.flag(save);
    }

    @GetMapping("/{id}")
    public R<Commodity> getById(@PathVariable Long id) {
        log.info("查询id为{}的商品", id);
        Commodity commodity = commodityService.getById(id);
        return R.success(commodity);
    }

    @PutMapping
    public R<String> update(@RequestBody Commodity commodity) {
        log.info("更改商品信息,商品信息==>{}", commodity.toString());
        boolean flag = commodityService.updateById(commodity);
        return R.flag(flag);

    }

    @PostMapping("/status/{status}")
    public R<String> status(@RequestParam List<Long> ids, @PathVariable int status) {
        // 0 更改为停售 1更改为起售
        log.info("更改售卖状态:ids==>{},status==>{}", ids, status);
        boolean flag = commodityService.updateStatus(ids, status);
        return flag ? R.success("商品状态已经更改成功！") : R.error("商品状态更改失败,请重试!");

    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        boolean flag = commodityService.removeByIds(ids);
        return flag ? R.success("删除成功!") : R.error("删除失败,请重试!");

    }

}
