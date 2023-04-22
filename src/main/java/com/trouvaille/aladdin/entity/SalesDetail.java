package com.trouvaille.aladdin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SalesDetail)实体类
 *
 * @author makejava
 * @since 2023-04-22 17:33:05
 */
@Data
public class SalesDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 订单id
     */
    private Long salesId;
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
    private Integer amount;
    /**
     * 图片
     */
    private String image;


}

