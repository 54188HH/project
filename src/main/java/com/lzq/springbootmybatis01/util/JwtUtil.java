package com.lzq.springbootmybatis01.util;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    /**
     * @param key    公共部分
	 * @param param  私有部分
	 * @param salt   签名部分
     * @return java.lang.String
     * @author yuxin
     * @creed: 生成token的方法
     * @date 2020/5/26 20:34
    */
    public static String encods(String key, Map<String,Object> param,String salt){
        if (salt != null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);
        //将用户信息放入 jwtBuilder 中
        jwtBuilder = jwtBuilder.setClaims(param);
        //生成token
        String token = jwtBuilder.compact();

        return token;
    }
    /**
     * @param token  生成的字符串token
	 * @param key    公共部分
	 * @param salt   私有部分
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author lzq
     * @describe:  解析token
     * @date 2020/5/26 20:39
    */
    public static Map<String,Object> decode(String token, String key, String salt){
        Claims claims = null;
        if (null != salt){
            key+=salt;
        }
        try{
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (JwtException e){
            return null;
        }
        return claims;
    }
    @Test
    public void method(){
        String key = "lzq";
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",1001);
        map.put("nickName","admin");
        String salt = "192.168.3.116";
        String token = encods(key,map,salt);
    System.out.println("token:" + token);
    Map<String,Object> map01 = decode(token,key,salt);
    System.out.println("解密:"+map01);
    }
}
