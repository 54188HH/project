package com.lzq.springbootmybatis01.service;

import org.springframework.stereotype.Service;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-03 09:54
 **/
@Service
public class BizUnitService {
    public String bizOne(String order) {
        return order + "各种花式操作1";
    }
    public String bizTwo(String order) {
        return order + "各种花式操作2";
    }
    public String bizThree(String order) {
        return order + "各种花式操作3";
    }
}