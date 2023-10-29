package com.rj.backendjixian.util;

import com.rj.backendjixian.model.entity.MerchantEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static String key = "tCo2ZgpY9JdzSVbuJ25f1r77NXJXti7g";  //密钥
    private static long Token_EXP = 1000 * 60 * 60 * 24;       //时间 一天

    //通过配置文件注入密钥和过期时间
//    @Value("${jwt.key}")
//    public  void setSecretKey(String secretKey) {
//        key = secretKey;
//    }
//    @Value("${jwt.Token_EXP}")
//    public  void setToken_EXP(long token_EXP) {
//        Token_EXP = token_EXP;
//    }

    /**
     * 获取转换后的私钥对象
     *
     * @return
     */
    public static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    /**
     * 生成token
     *
     * @param merchant
     * @return
     */
    public static String createToken(MerchantEntity merchant) {

        JwtBuilder jwtBuilder = Jwts.builder()
                .header()//头部信息
                .add("alg", "HS256") // 加密算法
                .add("typ", "JWT") //类别
                .and()
                .id(merchant.getId()) //卖家id
                .subject(merchant.getName())//卖家名
                .issuedAt(new Date())//登录时间
                .expiration(new Date(new Date().getTime() + Token_EXP))//设置过期时间
                .signWith(getSecretKey());
        return jwtBuilder.compact();
    }

    public static Claims parseToken(String jwt) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

    }

    /**
     * 验证JWT
     *
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


}
