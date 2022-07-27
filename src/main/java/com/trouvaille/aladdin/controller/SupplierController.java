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

@Slf4j
@RestController
@RequestMapping("/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    @GetMapping("/page")
    public R<Page<Supplier>> page(int page, int pageSize, String name) {
        log.info("Supplier:name, page, pageSize==>{},{},{}", name, page, pageSize);

        Page<Supplier> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Supplier> lqw = new LambdaQueryWrapper<>();

        lqw.like(name != null, Supplier::getName, name);
        lqw.orderByDesc(Supplier::getUpdateTime);

        supplierService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }


}
