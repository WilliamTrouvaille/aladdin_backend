package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Sales implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 顾客
     */
    private Long userId;

    /**
     * 购物车id
     */
    private Long shoppingCartId;


    /**
     * 地址
     */
    private Long addressId;


    /**
     * 支付方式 0到店支付 1外送
     */
    private Integer method;

    /**
     * 订单创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
