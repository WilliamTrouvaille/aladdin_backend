package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 供应商id
    private Long supplier;

    // 门店id
    private Long store;

    // 商品名称
    private String commodityName;

    // 商品id
    private Long commodityId;

    // 订单状态 1已支付，2待派送，3已派送，4已完成
    private Integer status;

    // 进货价 单位分
    private Integer purchasePrice;

    // 交接员工
    private Long employee;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 交接时间
    private LocalDateTime checkoutTime;

    // 数量
    private Integer number;

    // 备注
    private String description;


}
