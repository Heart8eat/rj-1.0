package com.rj.backendjixian.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    @Schema(description = "自定义响应码", requiredMode = Schema.RequiredMode.REQUIRED)
    private long code;
    @Schema(description = "响应信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;
    @Schema(description = "响应数据")
    private T data;

    /**
     * 成功无返回
     */
    public static <T> Response<T> success() {
        return new Response<T>(StatueCode.SUCCESS.getCode(), StatueCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>(StatueCode.SUCCESS.getCode(), StatueCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Response<T> success(T data, String message) {
        return new Response<T>(StatueCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> Response<T> failed(IStatueCode errorCode) {
        return new Response<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> Response<T> failed(IStatueCode errorCode, String message) {
        return new Response<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Response<T> failed(String message) {
        return new Response<T>(StatueCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Response<T> failed() {
        return failed(StatueCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Response<T> validateFailed() {
        return failed(StatueCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Response<T> validateFailed(String message) {
        return new Response<T>(StatueCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Response<T> unauthorized(T data) {
        return new Response<T>(StatueCode.UNAUTHORIZED.getCode(), StatueCode.UNAUTHORIZED.getMessage(), data);
    }
    public static <T> Response<T> unauthorized(String message) {
        return new Response<T>(StatueCode.UNAUTHORIZED.getCode(), message,null);
    }
    /**
     * 未授权返回结果
     */
    public static <T> Response<T> forbidden(T data) {
        return new Response<T>(StatueCode.FORBIDDEN.getCode(), StatueCode.FORBIDDEN.getMessage(), data);
    }

}
