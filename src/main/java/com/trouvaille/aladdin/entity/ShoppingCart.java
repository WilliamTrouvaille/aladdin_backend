package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 名称
    private String name;

    // 图片
    private String image;

    // 用户id
    private Long userId;

    // 商品id
    private Long commodityId;

    // 数量
    private Integer number;

    // 金额 单位分
    private Integer amount;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}