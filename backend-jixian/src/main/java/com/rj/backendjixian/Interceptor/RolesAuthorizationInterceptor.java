package com.rj.backendjixian.Interceptor;

import com.rj.backendjixian.annotation.RequiresRoles;
import com.rj.backendjixian.exception.RolesAuthorizationException;
import com.rj.backendjixian.util.Context;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Slf4j
@Component
public class RolesAuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {

            // 检查方法上是否存在RequiresRoles注解
            if (handlerMethod.getMethod().isAnnotationPresent(RequiresRoles.class)) {
                RequiresRoles requiresRoles=handlerMethod.getMethod().getAnnotation(RequiresRoles.class);
                return check_roles(requiresRoles);
            }

            // 检查类上是否存在RequiresRoles注解
            if (handlerMethod.getBeanType().isAnnotationPresent(RequiresRoles.class)) {
                RequiresRoles requiresRoles=handlerMethod.getBeanType().getAnnotation(RequiresRoles.class);
                return check_roles(requiresRoles);
            }
        }

        // 不需要拦截，返回true
        return true;
    }
    private boolean check_roles(RequiresRoles requiresRoles){
        String[] rolesAuth= requiresRoles.roles();
        String[] roles= Context.get("roles",String[].class);
        log.info("用户角色:{},认证角色:{}", Arrays.toString(roles),Arrays.toString(rolesAuth));
        boolean result=areRolesContained(roles,rolesAuth);
       if(result){
           log.info("角色认证通过");
       }else {
           throw new RolesAuthorizationException("角色认证未通过",rolesAuth);
       }
        return result;
    }
    /**
     * @param roles 用户角色
     * @param rolesAuth 认证角色（需要的角色）
     * @return 用户的角色数组是否包含要求的角色之一
     * **/
    private boolean areRolesContained(String[] roles, String[] rolesAuth) {
        for (String role : rolesAuth) {
            for (String userRole : roles) {
                if (role.equals(userRole)) {
                    return true;
                }
            }
        }
        return false;
    }
}
