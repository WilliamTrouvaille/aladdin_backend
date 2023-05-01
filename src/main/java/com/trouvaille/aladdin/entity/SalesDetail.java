package com.trouvaille.aladdin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (SalesDetail)表实体类
 *
 * @author trouvaille
 * @since 2023-05-01 23:07:50
 */
@Data
public class SalesDetail implements Serializable {

    private static final long serialVersionUID = 184463576525516573L;

    /**
     * 主键
     */
    @Id
    private Long id;


    /**
     * 名字
     */
    private String name;


    /**
     * 图片
     */
    private String image;


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
    private BigDecimal amount;

}
