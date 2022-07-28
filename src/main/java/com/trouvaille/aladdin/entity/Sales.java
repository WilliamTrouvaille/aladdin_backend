package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // 顾客
    private Long userId;

    // 商品id
    private Long commodity;

    // 员工
    private Long employee;

    // 地址
    private Long address;

    // 商品总数
    private Integer number;

    // 订单总利润 单位分
    private Integer profit;

    // 商品总额 单位分
    private Integer price;

    // 支付方式 0到店支付 1外送
    private Integer method;

    // 订单创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
