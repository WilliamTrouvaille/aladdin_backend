package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.SalesDetail;
import com.trouvaille.aladdin.service.SalesDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (SalesDetail)表控制层
 *
 * @author trouvaille
 * @since 2023-04-22 18:18:17
 */
@RestController
@Slf4j
@RequestMapping("/salesDetail")
public class SalesDetailController {

    @Autowired
    private SalesDetailService salesDetailService;


    @GetMapping("/list")
    public R<List<SalesDetail>> list() {

        List<SalesDetail> salesDetail = salesDetailService.list(null);
        log.info("SalesDetail--list===>{}", salesDetail);
        return R.success(salesDetail);
    }

    @GetMapping("/page")
    public R<Page<SalesDetail>> page(int page, int pageSize, String name) {
        log.info("SalesDetail--page:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<SalesDetail> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<SalesDetail> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, SalesDetail::getName, name);
        salesDetailService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }


    @GetMapping("/{id}")
    public R<SalesDetail> getById(@PathVariable Long id) {
        log.info("SalesDetail--getById:id==>{}", id);
        SalesDetail salesDetail = salesDetailService.getById(id);
        return R.success(salesDetail);
    }


    @PostMapping("/save")
    public R<String> save(@RequestBody SalesDetail salesDetail) {
        log.info("SalesDetail--save: salesDetail==>{}", salesDetail);
        boolean save = salesDetailService.save(salesDetail);
        return R.flag(save, "新增信息成功!", "新增信息失败,请重试!");
    }


    @PutMapping("/update")
    public R<String> update(@RequestBody SalesDetail salesDetail) {
        log.info("SalesDetail--update: salesDetail==>{}", salesDetail);
        boolean update = salesDetailService.updateById(salesDetail);
        return R.flag(update, "更改信息成功!", "更改信息失败,请重试!");
    }


}

