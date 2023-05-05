package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.trouvaille.aladdin.common.BaseContext;
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
@RequestMapping ("/address")
public class AddressController {
    
    @Autowired
    private AddressService addressService;
    
    
    /**
     * @param :
     * @return R < List  <  Address>>
     * @author willi
     * @description 查询全部地址信息
     * @date 2023/04/27 16:20
     */
    @GetMapping ("/list")
    public R<List<Address>> list () {
        Long userId = BaseContext.getCurrentId();
        log.info("当前用户id为==>{}" , userId);
        
        String redisKey = "address:list:" + userId;
        
        LambdaQueryWrapper<Address> lqw = new LambdaQueryWrapper();
        lqw.eq(Address::getUserId , userId);
        lqw.orderByDesc(Address::getUpdateTime);
        List<Address> addressList = this.addressService.list(lqw);
        
        
        return R.success(addressList);
    }
    
    /**
     * @param address:
     * @return R  <  String>
     * @author willi
     * @description 新增地址信息
     * @date 2023/04/27 16:21
     */
    @PostMapping ("/save")
    public R<String> save (@RequestBody Address address) {
        Long userId = BaseContext.getCurrentId();
        log.info("当前用户id为==>{}" , userId);
        
        
        address.setUserId(userId);
        log.info("Address--save: address==>{}" , address);
        boolean save = this.addressService.save(address);
        
        return R.flag(save , "新增信息成功!" , "新增信息失败,请重试!");
    }
    
    /**
     * @param address:
     * @return R  <  String>
     * @author willi
     * @description 修改地址信息
     * @date 2023/04/27 16:22
     */
    
    @PutMapping ("/update")
    public R<String> update (@RequestBody Address address) {
        log.info("Address--update: address==>{}" , address);
        
        boolean update = this.addressService.updateById(address);
        
        return R.flag(update , "更改信息成功!" , "更改信息失败,请重试!");
    }
    
    
    /**
     * @param id:
     * @return R  <  String>
     * @author willi
     * @description 删除地址信息
     * @date 2023/04/27 16:22
     */
    @DeleteMapping ("/{id}")
    public R<String> delete (@PathVariable Long id) {
        log.info("Address--delete: id==>{}" , id);
//        Address address = addressService.getById(id);
//        address.setIsDeleted(1);
        boolean delete = this.addressService.removeById(id);
        return R.flag(delete , "删除信息成功!" , "删除信息失败,请重试!");
        
    }
    
    /**
     * @param id:
     * @return R  <  Address>
     * @author willi
     * @description 根据id查询地址信息
     * @date 2023/04/27 16:23
     */
    
    @GetMapping ("/{id}")
    public R<Address> getById (@PathVariable Long id) {
        log.info("Address--getById:id==>{}" , id);
        Address address = this.addressService.getById(id);
        return R.success(address);
    }
    
    /**
     * @param address:
     * @return R  <  Address >
     * @author willi
     * @description 设置默认地址
     * @date 2023/04/27 15:37
     */
    @PutMapping ("/default")
    public R<Address> setDefault (@RequestBody Address address) {
        Long userId = BaseContext.getCurrentId();
        address.setUserId(userId);
        log.info("address:{}" , address);
        LambdaUpdateWrapper<Address> wrapper = new LambdaUpdateWrapper();
        wrapper.eq(Address::getUserId , BaseContext.getCurrentId());
        wrapper.set(Address::getIsDefault , 0);
        this.addressService.update(wrapper);
        address.setIsDefault(1);
        this.addressService.updateById(address);
        return R.success(address);
    }
    
    /**
     * @param :
     * @return R  <  Address>
     * @author willi
     * @description 获取用户默认地址
     * @date 2023/04/27 15:32
     */
    @GetMapping ("/default")
    public R<Address> getDefault () {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Address::getUserId , BaseContext.getCurrentId());
        queryWrapper.eq(Address::getIsDefault , 1);
        Address Address = (Address) this.addressService.getOne(queryWrapper);
        return null == Address ? R.error("没有找到该对象") : R.success(Address);
    }
    
    
}

