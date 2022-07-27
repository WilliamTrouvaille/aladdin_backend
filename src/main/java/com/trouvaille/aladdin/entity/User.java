package com.trouvaille.aladdin.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 姓名
    private String name;

    // 密码(md5加密) 可无
    private String password;

    // 手机号
    private String phone;

    // 性别
    private String sex;

    // 身份证号
    private String idNumber;

    // 头像
    private String avatar;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
