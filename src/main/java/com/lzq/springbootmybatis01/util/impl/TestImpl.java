package com.lzq.springbootmybatis01.util.impl;

import com.lzq.springbootmybatis01.util.Strategry;
import com.lzq.springbootmybatis01.util.Type;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-10 17:48
 **/
@Type(2)
@Service
public class TestImpl implements Strategry {
    @Override
    public BigDecimal calRecharge(Integer chanId, Integer goodsId) {
        return BigDecimal.valueOf(1.02);
    }
}
