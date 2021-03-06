package com.lzq.springbootmybatis01.controller;

import com.lzq.springbootmybatis01.entity.ManageInfo;
import com.lzq.springbootmybatis01.entity.User;
import com.lzq.springbootmybatis01.mapper.ManageInfoMapper;
import com.lzq.springbootmybatis01.mapper.UserMappping;
import com.lzq.springbootmybatis01.service.UserService;
import com.lzq.springbootmybatis01.util.ContextStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private UserMappping mapper;
    @Resource
    private UserService service;
    @Autowired
    private ManageInfoMapper manageInfoMapper;
    @RequestMapping("/enheng")
    public List<User> method(){
        List<User> list= mapper.method();
        return list;

    }
    @RequestMapping("/selectList")
    public User selectOne(int id){
        return mapper.selectOne(id);
    }
    /**
    * @Title: TestController
    * @Description: (用注解的方式代替if else)
    * @author lzq
    * @params
    * @return
    * @date 2020/5/10
    * @version V1.0
    */
    /*@Test
    public void typeStatus() throws Exception {
        ContextStatus contextStatus = new ContextStatus();
        BigDecimal bigDecimal = contextStatus.calRecharge(1,111);
        System.out.println(bigDecimal.setScale(2)+" ");
    }*/
    @RequestMapping("/testType")
    public void testMethod() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ContextStatus contextStatus = new ContextStatus();
        BigDecimal bigDecimal = contextStatus.calRecharge(1,111);
        System.out.println(bigDecimal.setScale(2)+" ");
    }
    @RequestMapping("/testUser")
    public User selectUserById(Integer id){
        System.out.println("来了老弟");
        return  service.selectUserByTemplate(id);
    }
    @RequestMapping("/oneMany")
    @ResponseBody
    public List<ManageInfo> mm(){
        List<ManageInfo> list = manageInfoMapper.selectByPrimaryKey();
        return list;
    }
}
