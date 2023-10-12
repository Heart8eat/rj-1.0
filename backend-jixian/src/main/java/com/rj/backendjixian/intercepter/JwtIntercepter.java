package com.rj.backendjixian.intercepter;

import com.rj.backendjixian.service.IMerchantService;
import com.rj.backendjixian.util.JwtUtil;
import com.rj.backendjixian.util.LoginToken;
import com.rj.backendjixian.util.PassToken;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

public class JwtIntercepter implements HandlerInterceptor {
    @Autowired
    private IMerchantService merchantService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        String token = request.getHeader("token");      //取token
//        String token_test = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiODFhNjg3MTFlZTc0YzhjYmExNzkxZjRiMGYzZDA5NiIsInN1YiI6Im0xIiwiaWF0IjoxNjk3MDgxNDAyLCJleHAiOjE2OTcxNjc4MDJ9.A1EUf4gtyGv_nx_REbhkv32W1lJNrdsWmcMO3Ae0rHk";

        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有 pass token 注释，如果有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passTokenAnnotation = method.getAnnotation(PassToken.class);
            if (passTokenAnnotation.required()) {
                System.out.println("pass token");
                return true;
            }
        }

        // 检查是否有需要 用户权限 的注解
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken LoginTokenAnnotation = method.getAnnotation(LoginToken.class);
            if (LoginTokenAnnotation.required()) {

                if (token == null) {
                    throw new RuntimeException("no token info.");
                }

                Claims resToken = JwtUtil.parseToken(token);
                System.out.println(resToken);
                return true;
            }
            return true;
        }

        return true;
/*
        if (token_test==null) {
//                wrapper.sendRedirect("fail/token/null");
            System.out.println("null token");
            return false;
        }
        if (null == JwtUtil.parseToken(token_test)) {
//          wrapper.sendRedirect("fail/token/invalid");
            System.out.println("invalid token");
            return false;
        }
        System.out.println("valid token");
        return true;*/
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器处理结束...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("请求结束...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

    }
}
