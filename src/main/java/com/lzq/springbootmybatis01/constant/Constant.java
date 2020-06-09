package com.lzq.springbootmybatis01.constant;

import org.springframework.stereotype.Component;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-11 19:32
 **/
public class Constant {
    public final static String USER_KEY="redis:user:";
    public final static String CONSTANT_SECKILLGOODS_ID_PRBFIX="CONSTANT_SECKILLGOODS_ID_PRBFIX_";
    public final static String CONSTANT_USERID_PRBFIX="CONSTANT_USERID_PRBFIX_";
    public final static String USERKEY_PREfIX="user:";
    public final static String USERINFOKEY_SUFfIX=":info";

    //cookie时间
    public static final int COOKIE_MAXAGB=7*24*3600;

    public static final String VERIFY_ADDRESS="http://localhost:8080/verify";

    public static final String LOGIN_ADDRESS="http://localhost:8080/index";
}
