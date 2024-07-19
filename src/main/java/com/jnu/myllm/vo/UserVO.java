package com.jnu.myllm.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long userId;

    /**
     * 用户名
     */
    private String username;


    private Integer status;

    /**
     * 用户密码
     */
    private String password;


}
