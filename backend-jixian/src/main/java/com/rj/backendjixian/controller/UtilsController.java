package com.rj.backendjixian.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.rj.backendjixian.model.entity.MerchantEntity;
import com.rj.backendjixian.model.vo.Response;
import com.rj.backendjixian.util.JwtUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@Tag(name = "调试用接口")
@CrossOrigin
@RequestMapping("/utils")
public class UtilsController {
    @GetMapping("/hashedPassword")
    @Operation(summary = "获取hash后的密码,可用于手动向数据库添加密码")
    @Parameters(value = {
            @Parameter(name = "password", description = "原密码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public Response<String> hashedPassword(@RequestParam String password){
        String hashedPassword=BCrypt.hashpw(password, BCrypt.gensalt());
        return Response.success(hashedPassword);
    }

    @GetMapping("/token")
    @Operation(summary = "获取365天商家token")
    public Response<String> token(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .header()//头部信息
                .add("alg", "HS256") // 加密算法
                .add("typ", "JWT") //类别
                .and()
                .id(MerchantEntity
                        .builder()
                        .id("b81a68711ee74c8cba1791f4b0f3d096")
                        .build().getId()) //卖家id
                .subject("merchant")//卖家名
                .issuedAt(new Date())//登录时间
                .expiration(new Date(new Date().getTime() + 365*24*60*60*1000))
                .signWith(JwtUtil.getSecretKey());//设置过期时间
        return Response.success(jwtBuilder.compact());
    }
}
