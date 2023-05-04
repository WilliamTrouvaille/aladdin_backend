package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 地址信息表(Address)表实体类
 *
 * @author trouvaille
 * @since 2023-04-27 14:07:01
 */
@SuppressWarnings ("serial")
@Data
public class Address extends Model<Address> {
    //主键
    private Long id;
    //用户id
    private Long userId;
    //收货人
    private String consignee;
    //手机号
    private String phone;
    //省级名称
    private String provinceName;
    //市级名称
    private String cityName;
    //区级名称
    private String districtName;
    //标签
    private String label;
    //详细地址
    private String detail;
    //默认 0 否 1是
    private Integer isDefault;
    /**
     * 创建时间
     */
    @TableField (fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField (fill = FieldFill.INSERT)
    private Long createUser;
    
    /**
     * 修改人
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    /**
     * 是否删除
     */
    private Integer isDeleted;
    
}
