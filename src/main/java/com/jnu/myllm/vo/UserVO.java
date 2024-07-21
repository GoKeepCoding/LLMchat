package com.jnu.myllm.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserVO {

    private String userId;

    /**
     * 用户名
     */
    private String username;

    private String nickName;

    private String telephone;

    private String email;

    private String sex;

    private Integer status;

    private Integer userTokens;

    /**
     * 用户密码
     */
    private String token;


}
