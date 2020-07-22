package com.lzq.springbootmybatis01.controller;

import com.lzq.springbootmybatis01.entity.User;
import com.lzq.springbootmybatis01.mapper.UserMappping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-21 09:29
 **/
@RestController
public class LockController {
    @Autowired
    private UserMappping mapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/updateById")
    @Transactional
    public Integer updateById(int id) throws InterruptedException {
        Integer sum =  mapper.updateById(id);
        TimeUnit.SECONDS.sleep(40);
        return sum;
    }

    @RequestMapping("/selectByUserId")
    public User selectByUserId(int id){
        return mapper.selectOne(id);
    }
    @RequestMapping("/redisLister")
    public void listenRedis(){
        for (int i = 0; i < 100; i++) {
            int n = i;
            new Thread(()->{
                redisTemplate.opsForValue().set("lzqtest"+n,"ceshi"+n,20,TimeUnit.SECONDS);
            }).start();
        }
    }
}
