package com.rj.backendjixian.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatueCode implements IStatueCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    NOT_ACCEPTABLE(406,"不支持该类型文件");


    private final long code;
    private final String message;

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
