package com.rj.backendjixian.handler;

import com.rj.backendjixian.exception.LoginException;
import com.rj.backendjixian.exception.RolesAuthorizationException;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.model.vo.StatueCode;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Arrays;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 处理空指针的异常
     *
     * @param e
     * @return 统一返回体
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Response<Void> exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常:-------------->{}", e.getMessage());
        return Response.failed(StatueCode.FAILED);
    }

    /**
     * 处理参数验证错误的异常
     *
     * @param e
     * @return 统一返回体
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)

    public Response<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String message = objectError != null ? objectError.getDefaultMessage() : null;
        log.error("发生参数验证错误异常:-------------->{}", message);
        return Response.validateFailed(message);
    }

    @ExceptionHandler(value = UncategorizedSQLException.class)

    public Response<Void> uncategorizedSQLExceptionHandler(UncategorizedSQLException e) {
        log.error("发生数据库错误异常:-------------->{}\n" +
                "发生异常的sql语句:-------------->{}", e.getMessage(), e.getSql());
        return Response.validateFailed(e.getMessage());
    }

    @ExceptionHandler(value = IOException.class)
    public Response<Void> iOExceptionHandler(IOException e) {
        log.error("发生IO错误异常:-------------->{}\n", e.getMessage());
        return Response.failed(StatueCode.NOT_ACCEPTABLE, e.getMessage());
    }

    @ExceptionHandler(value = JwtException.class)

    public Response<Void> jwtExceptionHandler(JwtException e) {
        log.error("发生token验证错误异常:-------------->{}\n", e.getMessage());
        return Response.unauthorized(e.getMessage());
    }
    @ExceptionHandler(value = RolesAuthorizationException.class)

    public Response<Void> rolesAuthorizationExceptionHandler(RolesAuthorizationException e) {
        log.error("""
                发生角色认证未通过异常:-------------->{}
                缺失角色-------------->{}
                """, e.getMessage(), Arrays.toString(e.getMissingRole()));
        return Response.forbidden(e.getMessage());
    }
    @ExceptionHandler(value = LoginException.class)
    public Response<Void> loginExceptionHandler(LoginException e) {
        log.error("发生登录失败异常:-------------->{}尝试登录失败\n,错误信息:{}\n", e.getLoginName(), e.getMessage());
        return Response.failed(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response<Void> runtimeExceptionHandler(RuntimeException e) {
        log.error("发生运行中异常:-------------->{}\n", e.getMessage());
        return Response.failed(e.getMessage());
    }
}
