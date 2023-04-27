package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Address;
import com.trouvaille.aladdin.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址信息表(Address)表控制层
 *
 * @author trouvaille
 * @since 2023-04-27 14:07:01
 */
@RestController
@Slf4j
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/list")
    public R<List<Address>> list() {

        List<Address> address = addressService.list(null);
        log.info("Address--list===>{}", address);
        return R.success(address);
    }

    @GetMapping("/page")
    public R<Page<Address>> page(int page, int pageSize, String name) {
        log.info("Address--page:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<Address> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Address> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Address::getUpdateTime);
        addressService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }


    @GetMapping("/{id}")
    public R<Address> getById(@PathVariable Long id) {
        log.info("Address--getById:id==>{}", id);
        Address address = addressService.getById(id);
        return R.success(address);
    }


    @PostMapping("/save")
    public R<String> save(@RequestBody Address address) {
        log.info("Address--save: address==>{}", address);
        boolean save = addressService.save(address);
        return R.flag(save, "新增信息成功!", "新增信息失败,请重试!");
    }


    @PutMapping("/update")
    public R<String> update(@RequestBody Address address) {
        log.info("Address--update: address==>{}", address);
        boolean update = addressService.updateById(address);
        return R.flag(update, "更改信息成功!", "更改信息失败,请重试!");
    }


    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        log.info("Address--delete: id==>{}", id);
        Address address = addressService.getById(id);
        boolean delete = addressService.updateById(address);
        return R.flag(delete, "删除信息成功!", "删除信息失败,请重试!");

    }
}

