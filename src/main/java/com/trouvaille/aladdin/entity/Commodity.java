package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 商品名称
    private String name;

    // 商品价格 单位 分
    private Integer price;

    // 条形码
    private String code;

    // 进货价 单位分
    private Integer purchasePrice;

    // 会员折扣率 单位%
    private Integer discount;

    // 图片
    private String image;

    // 描述信息
    private String description;

    // 规格
    private String specification;

    // 产地
    private String producePlace;

    // 库存数量
    private Integer number;

    // 供应商id
    private Long supplierId;

    // 供应商名称
    private String supplierName;

    // 0 停售 1 起售
    private Integer status;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // 修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    // 是否删除
    private Integer isDeleted;


}
