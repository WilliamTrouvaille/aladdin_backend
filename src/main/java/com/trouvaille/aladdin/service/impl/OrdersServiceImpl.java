/**
 * @projectName: aladdin
 * @package: com.trouvaille.aladdin.service.impl
 * @className: OrdersServiceImpl
 * @author: Eric
 * @description: 订单ServiceImpl
 * @date: 2022/7/27 21:11
 * @version: 1.0
 */
package com.trouvaille.aladdin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trouvaille.aladdin.entity.Orders;
import com.trouvaille.aladdin.mapper.OrdersMapper;
import com.trouvaille.aladdin.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}

