package com.lzq.springbootmybatis01.config;

import com.alibaba.fastjson.JSON;
import com.lzq.springbootmybatis01.constant.Constant;
import io.jsonwebtoken.impl.Base64UrlCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-05-27 11:11
 **/
@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Value("{$token.key}")
    private String key;
    //拦截之前执行  进入controller控制器之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取newtoken
        String token = request.getParameter("newToken");
        //如果token不为null  就把token放到cookie里面
        if (null != token){
            //将token存入cookie
            CookieUtil.addCookie(response,"token",token,Constant.COOKIE_MAXAGB);

        }else if (null == token){
            //如果url里面的token为空，就直接取
            token = CookieUtil.getCookie(request,"token");
        }

        if (null != token){
            Map map = getUserNameByToken(token);
            String nickName = String.valueOf(map.get("nickName"));
            request.setAttribute("nickName",nickName);
        }
        return true;
    }

    //进入控制器之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    //视图渲染之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

    private Map getUserNameByToken(String token){
        //获取token中的中间部分
        String tokenAdmin = StringUtils.substringBetween(token, ".");
        //将tokenAdmin进行base64解码
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        //解码之后得到byte数组
        byte[] decode = base64UrlCodec.decode(tokenAdmin);
        String mapJson = null;
        try {
            mapJson = new String(decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //将字符串转换为map
        return JSON.parseObject(mapJson,Map.class);
    }
}
