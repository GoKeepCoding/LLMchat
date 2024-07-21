package com.jnu.myllm.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private String username;

    private String password;

    private String nickName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String telephone;


}
