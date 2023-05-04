package com.trouvaille.aladdin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单信息表(Sales)表实体类
 *
 * @author trouvaille
 * @since 2023-05-01 23:07:50
 */
@Data
public class Sales implements Serializable {
    
    private static final long serialVersionUID = 546783934683399378L;
    
    /**
     * 主键
     */
    @Id
    private Long id;
    
    
    /**
     * 订单号
     */
    private String number;
    
    
    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;
    
    
    /**
     * 下单用户
     */
    private Long userId;
    
    
    /**
     * 地址id
     */
    private Long addressId;
    
    
    /**
     * 下单时间
     */
    private LocalDateTime orderTime;
    
    
    /**
     * 结账时间
     */
    private LocalDateTime checkoutTime;
    
    
    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;
    
    
    /**
     * 实收金额
     */
    private BigDecimal amount;
    
    
    /**
     * 备注
     */
    private String remark;
    
    
    /**
     * ${column.comment}
     */
    private String phone;
    
    
    /**
     * ${column.comment}
     */
    private String address;
    
    
    /**
     * ${column.comment}
     */
    private String userName;
    
    
    /**
     * ${column.comment}
     */
    private String consignee;
    
}
