package com.rj.backendjixian.handler;

import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.model.vo.StatueCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

;

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
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Response<Void> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().stream().findFirst().orElse(null);
        String message = objectError != null ? objectError.getDefaultMessage() : null;
        log.error("发生参数验证错误异常:-------------->{}", message);
        return Response.validateFailed(message);
    }
    @ExceptionHandler(value = UncategorizedSQLException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<Void> uncategorizedSQLExceptionHandler(UncategorizedSQLException e) {
        log.error("发生数据库错误异常:-------------->{}\n" +
                "发生异常的sql语句:-------------->{}", e.getMessage(),e.getSql());
        return Response.validateFailed(e.getMessage());
    }
    @ExceptionHandler(value = IOException.class)
    public Response<Void> iOExceptionHandler(IOException e) {
        log.error("发生IO错误异常:-------------->{}\n" ,e.getMessage());
        return Response.failed(StatueCode.NOT_ACCEPTABLE,e.getMessage());
    }
}
