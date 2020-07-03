package com.lzq.springbootmybatis01.controller;

import com.lzq.springbootmybatis01.entity.ManageInfo;
import com.lzq.springbootmybatis01.mapper.ManageInfoMapper;
import com.lzq.springbootmybatis01.service.BizService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-02 10:15
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestClass {
    @Autowired
    private ManageInfoMapper manageInfoMapper;
    @Autowired
    private BizService bizService;

    /**
     * 使用嵌套结果方式查询一对多关系
     */
    @Test
    public void mm(){
        List<ManageInfo> list = manageInfoMapper.selectByPrimaryKey();
        System.out.println(list.size());
        System.out.println(list.toString());
    }

    /**
     * 使用策略模式优化if else
     * */
    @Test
    public void md(){
        System.out.println( bizService.getCheckResultMuti("1234567u8i", 1));
    }
}
