package com.trouvaille.aladdin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车信息表(ShoppingCart)表实体类
 *
 * @author trouvaille
 * @since 2023-05-01 23:07:50
 */
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -51746205873531611L;

    /**
     * 主键
     */
    @Id
    private Long id;


    /**
     * 名称
     */
    private String name;


    /**
     * 图片
     */
    private String image;


    /**
     * 用户id
     */
    private Long userId;


    /**
     * 商品id
     */
    private Long commodityId;


    /**
     * 数量
     */
    private Integer number;


    /**
     * 金额
     */
    private BigDecimal amount;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
