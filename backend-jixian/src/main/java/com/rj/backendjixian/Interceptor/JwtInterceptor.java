package com.rj.backendjixian.Interceptor;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.StrUtil;
import com.rj.backendjixian.annotation.LoginToken;
import com.rj.backendjixian.annotation.Role;
import com.rj.backendjixian.model.entity.BuyerEntity;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.model.entity.ShopEntity;
import com.rj.backendjixian.service.IBuyerService;
import com.rj.backendjixian.service.IMerchantService;
import com.rj.backendjixian.service.IShopService;
import com.rj.backendjixian.util.Context;
import com.rj.backendjixian.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

import static com.rj.backendjixian.model.entity.table.ShopEntityTableDef.SHOP_ENTITY;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    IMerchantService merchantService;
    @Autowired
    IShopService shopService;
    @Autowired
    IBuyerService buyerService;
    @Autowired
    TimedCache<String,Object> timedCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if (handler instanceof HandlerMethod handlerMethod) {
            String token = request.getHeader("Authorization");      //取token
            Method method = handlerMethod.getMethod();

            // 检查是否有需要 用户权限 的注解
            if (method.isAnnotationPresent(LoginToken.class)) {
               return checkToken(token);
            }
            // 检查类上是否需要
            if(handlerMethod.getBeanType().isAnnotationPresent(LoginToken.class)){
                return checkToken(token);
            }

        }
        return true;
    }
    /**
     * @param token jwt令牌
     * @return true 认证通过 false认证失败
     * **/
    private boolean checkToken(String token){
        if (StrUtil.isEmpty(token)) {
            throw new JwtException("no token info.");
        }
        // 检查token前缀是否正确
        if (!token.startsWith("Bearer ")) {
            throw new MalformedJwtException("token prefix must be Bearer!");
        }
        token = token.replace("Bearer ", "");

        Claims resToken = JwtUtil.parseToken(token);

        // 分配角色
        Context.put("roles",new String[]{resToken.getSubject()});
        if (resToken.getSubject().equals(Role.MERCHANT)) {
            // 从缓存中取值，如果缓存中没有则查询数据库
            MerchantEntity merchant = (MerchantEntity) timedCache.get(resToken.getId()+"-merchant");
            ShopEntity shop = null;

            if (merchant == null) {
                log.info("缓存过期");
                merchant = merchantService.getById(resToken.getId());
                if (merchant != null) {
                    timedCache.put(resToken.getId()+"-merchant", merchant);
                    shop = shopService.getOne(SHOP_ENTITY.MERCHANTS_ID.eq(merchant.getId()));
                    if (shop != null) {
                        timedCache.put(merchant.getId()+"-shop", shop);
                    }
                }
            } else {
                shop = (ShopEntity) timedCache.get(merchant.getId()+"-shop");
                if (shop == null) {
                    shop = shopService.getOne(SHOP_ENTITY.MERCHANTS_ID.eq(merchant.getId()));
                    if (shop != null) {
                        timedCache.put(merchant.getId()+"-shop", shop);
                    }
                }
            }

            // 将商家的详细信息放入上下文
            Context.put("merchant", merchant);
            Context.put("shop", shop);
//            log.info(resToken.toString());
            return true;
        }

        if (resToken.getSubject().equals(Role.BUYER)) {
            // 从缓存中取值，如果缓存中没有则查询数据库
            BuyerEntity buyerEntity = (BuyerEntity) timedCache.get(resToken.getId());

            if (buyerEntity == null) {
                log.info("缓存过期");
                buyerEntity = buyerService.getById(resToken.getId());
                if (buyerEntity != null) {
                    timedCache.put(resToken.getId(), buyerEntity);
                }
            }
            // 将买家的详细信息放入上下文
            Context.put("buyer", buyerEntity);
//            log.info(resToken.toString());
            return true;
        }
        return false;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        log.info("拦截器处理结束...");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        log.info("请求结束...");
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//
//    }
}
