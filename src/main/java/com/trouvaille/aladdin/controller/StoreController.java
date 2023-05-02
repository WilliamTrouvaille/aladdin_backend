/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: StoreController
 * @author: trouvaille_william
 * @description: 店铺控制器
 * @date: 2022/7/27 20:21
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Store;
import com.trouvaille.aladdin.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/store")
/**
 * @deprecated
 * 已弃用!!!!!!!!
 */
@Deprecated
public class StoreController {

    @Autowired
    private StoreService storeService;


    @GetMapping("/page")
    public R<Page<Store>> page(int page, int pageSize, String name) {
        log.info("Page --Store:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<Store> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Store> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Store::getName, name);
        lqw.orderByDesc(Store::getUpdateTime);
        storeService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }

    @GetMapping("/list")
    public R<List<Store>> list() {
        log.info("获取全部门店信息");
        List<Store> stores = storeService.list(null);
        return R.success(stores);
    }

    @PostMapping("/save")
    public R<String> save(@RequestBody Store store) {
        log.info("新增门店信息==>{}", store);
        boolean save = storeService.save(store);
        return R.flag(save, "新增门店成功!", "新增门店失败,请重试!");
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody Store store) {
        log.info("更改门店信息==>{}", store);
        boolean flag = storeService.updateById(store);
        return R.flag(flag, "更改门店成功!", "更改门店失败,请重试!");
    }

    @GetMapping("/{id}")
    public R<Store> getById(@PathVariable Long id) {
        log.info("根据id查询门店信息==>{}", id);
        Store store = storeService.getById(id);
        return R.success(store);
    }
}
