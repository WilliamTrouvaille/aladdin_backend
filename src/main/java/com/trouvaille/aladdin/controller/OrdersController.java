/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.controller
 * @className: OrdersController
 * @author: trouvaille_william
 * @description: 订单控制类
 * @date: 2022/7/27 21:12
 * @version: 1.0
 */
package com.trouvaille.aladdin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trouvaille.aladdin.common.R;
import com.trouvaille.aladdin.entity.Orders;
import com.trouvaille.aladdin.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;


    @GetMapping("/page")
    public R<Page<Orders>> page(int page, int pageSize, String name) {
        log.info("Orders:name, page, pageSize==>{},{},{}", name, page, pageSize);
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null, Orders::getCommodityName, name);
        lqw.orderByDesc(Orders::getCreateTime);
        ordersService.page(pageInfo, lqw);
        return R.success(pageInfo);
    }

}
