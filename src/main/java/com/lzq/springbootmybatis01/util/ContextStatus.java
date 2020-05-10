package com.lzq.springbootmybatis01.util;

import java.math.BigDecimal;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-10 17:03
 **/
public class ContextStatus  {
    private Strategry strategry;
    public BigDecimal calRecharge(Integer chanId, Integer goodsId) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //根据type内的值  把需要实现的方法return过来
        StrategryFactory strategryFactory = StrategryFactory.getInstance();

        strategry = strategryFactory.create(chanId);
        return strategry.calRecharge(chanId,goodsId);
    }
}
