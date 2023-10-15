package com.rj.backendjixian.util;

import com.rj.backendjixian.model.entity.MerchantEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {

    private static String SecretKey;  //密钥
    private static long Token_EXP;       //时间 一天
    final static String id = "b81a68711ee74c8cba1791f4b0f3d096";
    final static String name = "m1";
    //通过配置文件注入密钥和过期时间
    @Value("${jwt.SecretKey}")
    public  void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
    @Value("${jwt.Token_EXP}")
    public  void setToken_EXP(long token_EXP) {
        Token_EXP = token_EXP;
    }

    /**
     * 生成token
     * @param merchant
     * @return
     */
    public static String createToken(MerchantEntity merchant){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(merchant.getId()+"")     //卖家id
                .setSubject(merchant.getName())     //卖家名
                .setIssuedAt(new Date())        //登录时间
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .setExpiration(new Date(new Date().getTime() + Token_EXP));      //设置过期时间
        System.out.println(jwtBuilder.compact());
        return jwtBuilder.compact();
    }

    public static Claims parseToken(String jwt){
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     * @throws JSONException
     */
    public static JSONObject validateJWT(String jwtStr) throws JSONException {
        JSONObject pojo = new JSONObject();
        Claims claims = null;
        try {
            claims = parseToken(jwtStr);
            pojo.put("Success", true);
            pojo.put("Claims", claims);
        } catch (ExpiredJwtException e) {
            pojo.put("Success", false);
            pojo.put("ErrCode", 1005);
            e.printStackTrace();
        } catch (Exception e) {
            pojo.put("Success", false);
            pojo.put("ErrCode", 1004);
            e.printStackTrace();
        }
        //返回success为null则校验失败
        return pojo;
    }


    public static void main(String[] args) throws Exception{
        MerchantEntity merchantEntity = new MerchantEntity(id, name,null,null);
        String a = createToken(merchantEntity);
        Claims c = parseToken(a);
        System.out.println(c.getId());
        System.out.println(c.getSubject());

    }

}
