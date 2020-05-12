package com.lzq.springbootmybatis01.service.impl;

import com.lzq.springbootmybatis01.Entity.User;
import com.lzq.springbootmybatis01.Mapper.UserMappping;
import com.lzq.springbootmybatis01.component.CacheTemplate;
import com.lzq.springbootmybatis01.constant.Constant;
import com.lzq.springbootmybatis01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-11 19:29
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMappping userMapper;

    @Resource
    private CacheTemplate cacheTemplate;
    @Override
    public User selectUserById(Integer id) {
        //避免缓存击穿代码实现
        User user = (User) redisTemplate.opsForValue().get(Constant.USER_KEY+id);
        if (null == user){
            synchronized (this){
                user = (User) redisTemplate.opsForValue().get(Constant.USER_KEY+id);
                if (null == user){
                    user=userMapper.selectOne(id);
                    redisTemplate.opsForValue().set(Constant.USER_KEY+id,user);
                }
            }
        }
        return user;
    }

    @Override
    public User selectUserByTemplate(Integer id) {
        User  user = cacheTemplate.loadCache(Constant.USER_KEY+id,Long.valueOf(10),()->{
            return userMapper.selectOne(id);
        });
        return  user;
    }

}
