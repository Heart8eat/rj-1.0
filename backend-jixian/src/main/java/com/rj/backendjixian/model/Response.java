package com.rj.backendjixian.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "统一返回体")
public class Response<T>{
    @Schema(example = "200")
    private long code;
    @Schema(example = "操作成功")
    private String message;
    private T data;
    /**
     * 成功无返回
     *
     */
    public static <T> Response<T> success() {
        return new Response<T>(StatueCode.SUCCESS.getCode(), StatueCode.SUCCESS.getMessage(),null);
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
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> Response<T> success(T data, String message) {
        return new Response<T>(StatueCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> Response<T> failed(IStatueCode errorCode) {
        return new Response<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> Response<T> failed(IStatueCode errorCode,String message) {
        return new Response<T>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
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

    /**
     * 未授权返回结果
     */
    public static <T> Response<T> forbidden(T data) {
        return new Response<T>(StatueCode.FORBIDDEN.getCode(), StatueCode.FORBIDDEN.getMessage(), data);
    }

}
