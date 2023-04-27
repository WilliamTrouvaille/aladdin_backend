package com.trouvaille.aladdin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品分类表(Category)表实体类
 *
 * @author trouvaille
 * @since 2023-04-27 21:27:39
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -31390253809755954L;

    /**
     * 主键
     */
    @Id
    private Long id;


    /**
     * 分类名称
     */
    private String name;


    /**
     * 顺序
     */
    private Integer sort;


    /**
     * 是否被删除
     */
    private Integer isDeleted;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
