package com.rj.backendjixian.aspect;

import com.rj.backendjixian.annotation.PassToken;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Aspect
@Component
@Order(1)
@Slf4j
public class InterceptorAspect {
    @Pointcut("execution(public * com.rj.backendjixian.Interceptor.*.preHandle(..))")
    public void interceptor() {
    }
    @Around("interceptor()")
    public boolean skipInterceptors(ProceedingJoinPoint joinPoint) throws Throwable {
        // 之前
        Object[] args = joinPoint.getArgs();
        String InterceptorName=joinPoint.getSignature().getDeclaringTypeName();
        for (Object arg : args) {
            if (arg instanceof HandlerMethod handlerMethod) {
                //检查是否有 pass token 注释，如果有则跳过认证
                if (handlerMethod.getMethod().isAnnotationPresent(PassToken.class)) {
                    log.info("pass {}",InterceptorName);
                    return true;
                }
                if(handlerMethod.getBeanType().isAnnotationPresent(PassToken.class)){
                    log.info("pass {}",InterceptorName);
                    return true;
                }
                break;
            }
        }
        // 调用原始方法，获取原始返回值
        return (boolean) joinPoint.proceed();
    }
}
