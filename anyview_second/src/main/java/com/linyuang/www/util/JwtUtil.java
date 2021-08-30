package com.linyuang.www.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    private static final String SIGNATURE = "!Q@W#E$R";
    /**
     *生成token
     * @param map 要放入payload的键值对数据
     * @return java.lang.String
     */
    public static String getToken(Map<String,String> map){
        Calendar calendar = Calendar.getInstance();
        //设置15天为token过期时间
        calendar.add(Calendar.DATE,15);
        //创建builder
        JWTCreator.Builder builder = JWT.create();
        //将map中的键值对放入token的payload处
        map.forEach(builder::withClaim);
        //设置过期时间以及签名
        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGNATURE));
    }
}
