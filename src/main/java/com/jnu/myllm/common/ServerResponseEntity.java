package com.jnu.myllm.common;

import com.jnu.myllm.constant.ResponseCode;
import lombok.Data;

@Data
public class ServerResponseEntity<T> implements java.io.Serializable {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ServerResponseEntity<T> success(int code,T data) {
        ServerResponseEntity<T> responseEntity = new ServerResponseEntity<>();
        responseEntity.setCode(code);
        responseEntity.setMsg(ResponseCode.SUCCESS.getMsg());
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ServerResponseEntity<T> error(String msg, T data) {
        ServerResponseEntity<T> responseEntity = new ServerResponseEntity<>();
        responseEntity.setCode(ResponseCode.ERROR.getCode());
        responseEntity.setMsg(msg);
        responseEntity.setData(data);
        return responseEntity;

    }

}
