package com.rj.backendjixian.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.rj.backendjixian.aspect.annotation.WebLog;
import com.rj.backendjixian.model.entity.WebLogEntity;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.rj.backendjixian.controller.MerchantController.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息(通过Logstash传入Elasticsearch)
        WebLogEntity webLogEntity = new WebLogEntity();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();


        // 如果被WebLog注解则为webLog实体赋值
        if (method.isAnnotationPresent(WebLog.class)) {
            WebLog webLog = method.getAnnotation(WebLog.class);
            if (webLog.isSaveResponseData()) {
                webLogEntity.setResult(result.toString());
            }
            if (webLog.isSaveRequestData()) {
                webLogEntity.setParameter(request.getQueryString());
            }
            if (method.isAnnotationPresent(Operation.class)) {
                Operation operation = method.getAnnotation(Operation.class);
                webLogEntity.setSummary(operation.summary());
                webLogEntity.setDescription(operation.description());

            }
            webLogEntity.setStartTime(new Date(startTime).toString());
            String urlStr = request.getRequestURL().toString();
            webLogEntity.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
            webLogEntity.setIp(request.getRemoteUser());
            webLogEntity.setSpendTime(String.valueOf(endTime - startTime));
            webLogEntity.setMethod(method.getName());
            webLogEntity.setUri(request.getRequestURI());
            webLogEntity.setUrl(request.getRequestURL().toString());
            log.info("{}", webLogEntity);
        }


        return result;
    }

}
