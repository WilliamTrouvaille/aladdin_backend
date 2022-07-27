/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: StoreController
 * @author: Eric
 * @description: TODO
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;


    @GetMapping("/page")
    public R<Page<Store>> page(int page, int pageSize, String name) {
        log.info("Store:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<Store> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Store> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Store::getName, name);
        lqw.orderByDesc(Store::getUpdateTime);
        storeService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }
}
