package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private Long id;
    
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 性别
     */
    private String sex;
    
    /**
     * 身份证号
     */
    private String idNumber;
    
    /**
     * 职位身份
     */
    private String identity;
    
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    
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
    
    
}
