/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: SupplierController
 * @author: Eric
 * @description: 供应商控制类
 * @date: 2022/7/27 21:24
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Supplier;
import com.trouvaille.aladdin.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    @GetMapping("/page")
    public R<Page<Supplier>> page(final int page, final int pageSize, final String name) {
        SupplierController.log.info("Supplier:name, page, pageSize==>{},{},{}", name, page, pageSize);

        final Page<Supplier> pageInfo = new Page<>(page, pageSize);
        final LambdaQueryWrapper<Supplier> lqw = new LambdaQueryWrapper<>();

        lqw.like(name != null, Supplier::getName, name);
        lqw.orderByDesc(Supplier::getUpdateTime);

        supplierService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }

    @GetMapping("/list")
    public R<List<Supplier>> list() {
        SupplierController.log.info("获取全部供应商信息");
        final List<Supplier> suppliers = supplierService.list(null);
        return R.success(suppliers);
    }

}
