package com.trouvaille.aladdin.controller;


import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.ShoppingCart;
import com.trouvaille.aladdin.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: F:/Study/homework/java/aladdin/aladdin
 * @package: F:/Study/homework/java/aladdin/aladdin
 * @className: ShoppingCartControllerController
 * @author: trouvaille
 * @description: ShoppingCartController控制类
 * @date: 2022-07-30 12:50:38
 * @version: 1.0
 */

@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/{id}")
    public R<ShoppingCart> getById(@PathVariable Long id) {
        log.info("ShoppingCart - getById:id==>{}", id);
        ShoppingCart shoppingCart = shoppingCartService.getById(id);
        return R.success(shoppingCart);
    }


    @PostMapping
    public R<String> save(@RequestBody ShoppingCart shoppingCart) {
        log.info("ShoppingCart - save:shoppingCart)=>{}", shoppingCart);
        boolean save = shoppingCartService.save(shoppingCart);
        return R.flag(save);
    }


    @PutMapping
    public R<String> update(@RequestBody ShoppingCart shoppingCart) {
        log.info("ShoppingCart - update:shoppingCart)=>{}", shoppingCart);
        boolean flag = shoppingCartService.updateById(shoppingCart);
        return R.flag(flag);
    }


    @DeleteMapping
    public R<String> delete(@RequestParam("idList") List<Long> ids) {
        log.info("ShoppingCart - delete:idList)=>{}", ids);
        boolean flag = shoppingCartService.removeByIds(ids);
        return R.flag(flag);
    }
}

