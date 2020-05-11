package com.lzq.springbootmybatis01.component;

import com.lzq.springbootmybatis01.service.BaseServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-11 20:22
 **/
@Component
public class CacheTemplate {
    @Autowired
    private RedisTemplate redisTemplate;

   public <T> T loadCache(String key,Long expires,BaseServiceHandler<T> service){
       //避免缓存击穿代码实现
       T result = (T) redisTemplate.opsForValue().get(key);
       if (null == result){
           synchronized (this){
               result = (T) redisTemplate.opsForValue().get(key);
               if (null == result){
                   result=service.loadData();
                   redisTemplate.opsForValue().set(key,result,expires,TimeUnit.SECONDS);
               }
           }
       }
       return result;
   }
}
