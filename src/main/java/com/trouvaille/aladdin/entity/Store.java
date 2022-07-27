package com.trouvaille.aladdin.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    // 门店id
    private Long id;

    // 手机号
    private Integer phone;

    // 门店名称
    private String name;

    // 联系人
    private String contact;

    // 地址
    private String address;


}
