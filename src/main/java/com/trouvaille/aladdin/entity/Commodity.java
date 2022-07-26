package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class Commodity implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;

    /**
     * 产地地址表编号
     */

    private Long produceplace;

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
     * 折扣率
     */
    private Integer rate;

    /**
     * 包装大小
     */
    private Integer size;

    /**
     * 量词
     */
    private String unit;

    /**
     * 商品码
     */
    private String code;

    /**
     * 图片
     */
    private String image;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 库存数量
     */
    private Integer number;

    /**
     * 临期数量
     */
    private Integer dangernum;

    /**
     * 0 停售 1 起售
     */
    private Integer status;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //创建人
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //修改人
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    private Integer isDeleted;

}
