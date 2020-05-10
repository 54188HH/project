package com.lzq.springbootmybatis01.util.impl;

import com.lzq.springbootmybatis01.Mapper.UserMappping;
import com.lzq.springbootmybatis01.util.BeanUtils;
import com.lzq.springbootmybatis01.util.Strategry;
import com.lzq.springbootmybatis01.util.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-10 16:57
 **/
@Type(1)
@Service
/**
*  继承 BeanUtils 用来解决@Resource 注入 空指针问题
*/
public class StrategryImpl extends BeanUtils implements Strategry {
    @Resource
    private UserMappping mapp;
    @Override
    public BigDecimal calRecharge(Integer chanId, Integer goodsId) {
        System.out.println(mapp.selectOne(1).getName());
        return BigDecimal.valueOf(1.01);
    }
}
