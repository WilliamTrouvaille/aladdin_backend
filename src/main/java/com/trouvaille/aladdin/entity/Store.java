package com.trouvaille.aladdin.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Store implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 门店id
     */
    private Long id;
    
    /**
     * 手机号
     */
    private Long phone;
    
    /**
     * 门店名称
     */
    private String name;
    
    /**
     * 联系人
     */
    private String contact;
    
    /**
     * 地址
     */
    private String address;
    
    private LocalDateTime createTime;
    
    
    private LocalDateTime updateTime;
    
    
    private Long createUser;
    
    
    private Long updateUser;
    
    
}
