package com.trouvaille.aladdin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品信息表(Commodity)表实体类
 *
 * @author trouvaille
 * @since 2023-04-27 22:45:14
 */
@Data
public class Commodity implements Serializable {
    
    private static final long serialVersionUID = 335153729424390700L;
    
    /**
     * 主键
     */
    @Id
    private Long id;
    
    
    /**
     * 种类id
     */
    private Long categoryId;
    
    
    /**
     * 月销量
     */
    private Long saleNum;
    
    
    /**
     * 商品名称
     */
    private String name;
    
    
    /**
     * 商品价格 单位 分
     */
    private Integer price;
    
    
    /**
     * 进货价 单位分
     */
    private Integer purchasePrice;
    
    
    /**
     * 图片
     */
    private String image;
    
    
    /**
     * 描述信息
     */
    private String description;
    
    
    /**
     * 规格
     */
    private String specification;
    
    
    /**
     * 产地
     */
    private String producePlace;
    
    
    /**
     * 库存数量
     */
    private Integer number;
    
    
    /**
     * 0 停售 1 起售
     */
    private Integer status;
    
    
    /**
     * 是否删除
     */
    private Integer isDeleted;
    
    
    /**
     * 创建时间
     */
    @TableField (fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 创建人
     */
    @TableField (fill = FieldFill.INSERT)
    private Long createUser;
    
    /**
     * 修改时间
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    /**
     * 修改人
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
