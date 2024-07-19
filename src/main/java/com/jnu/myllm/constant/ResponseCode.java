package com.jnu.myllm.constant;

public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),

    /**
     * 用户或请求尚未进行身份验证。
     * 这可能是因为缺少认证信息，或者认证信息提供的方式不正确。
     */
    UNAUTHORIZED(401, "用户未认证。请提供有效的认证信息。"), // HTTP 401 Unauthorized

    /**
     * 用户已进行身份验证，但没有足够的权限访问请求的资源。
     * 这可能是因为用户角色不足，或者缺少必要的权限令牌。
     */
    FORBIDDEN(403, "用户已认证，但无权访问请求的资源。请联系管理员获取权限。"), // HTTP 403 Forbidden


    /**
     * 请求的资源不存在，或者用户无法访问该资源。
     * 这可能是因为资源已被移除，或者权限策略已更改。
     */
    NOT_FOUND(404, "请求的资源不存在，或者您无权访问该资源。"), // HTTP 404 Not Found

    /**
     * 内部服务器错误，导致无法确定授权状态。
     * 这通常是由于系统错误或配置问题导致的。
     */
    INTERNAL_SERVER_ERROR(500, "内部服务器错误。请联系管理员以解决此问题。"); // HTTP 500 Internal Server Error


    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getMsg() {

        return desc;
    }
}
