package com.lzq.springbootmybatis01.service.impl;

import com.lzq.springbootmybatis01.Entity.Goods;
import com.lzq.springbootmybatis01.Entity.OrderRecord;
import com.lzq.springbootmybatis01.constant.Constant;
import com.lzq.springbootmybatis01.service.SeckillService;
import com.lzq.springbootmybatis01.thread.AsyncService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-05-20 15:30
 **/
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AsyncService asyncService;
    @Override
    public String saveOrder(Integer id, String userId) {
        //0.从用户的set集合中判断用户是否已经抢购过了 redisTemplate.boundSetOps();
         Boolean resoult = redisTemplate.boundSetOps(Constant.CONSTANT_USERID_PRBFIX+id).isMember(userId);
         if (resoult){
             return "您已经请购过了，本次秒杀限购一次";
         }
        //1.从队列中获取秒杀商品id
         id = (Integer) redisTemplate.boundListOps(Constant.CONSTANT_SECKILLGOODS_ID_PRBFIX+id).rightPop();
        //2.判断商品是否存在
        if (null==id){
            //3.商品不存在，或库存《=0，返回失败，提示已售空
            return  "商品已售空，请选择其他商品";
        }

         //4.将用户放入用户集合内
        redisTemplate.boundSetOps(Constant.CONSTANT_USERID_PRBFIX+id).add(userId);

        //5.创建OrderRecord对象  记录userid和商品id
        OrderRecord orderRecord = new OrderRecord(id,userId);
        redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).leftPush(orderRecord);

        //多线程异步插入数据库订单表
        asyncService.executeAsync();

        return "秒杀成功";
    }
    @Test
    public void me(){
        importToRedis();
    }
    public void importToRedis(){
        //1.查询合法的秒杀商品数据：秒杀状态为1 库存量 > 0  秒杀结束时间》当前时间》秒杀开始时间
        List<Goods> list = new ArrayList<Goods>();
        Goods goods = new Goods();
        goods.setGoodsName("1+手机");
        goods.setPrice(new BigDecimal(3500.00));
        goods.setStock(50);
        goods.setId(1);
        Goods g = new Goods();
        g.setGoodsName("外星人笔记本");
        g.setPrice(new BigDecimal(9500.00));
        g.setStock(50);
        g.setId(2);
        list.add(g);
        list.add(goods);
        //2.吧合法秒杀的商品数据放入redis
           for (Goods g1 :list){
               redisTemplate.boundHashOps(Goods.class.getSimpleName()).put(goods.getId(),g1);
               //为每个商品创建一个队列，队列中放和库存量相同数量的商品id
               createQueue(g1.getId(),g1.getStock());
           }
    }
    private void createQueue(Integer id,Integer stockCount){
        if (stockCount>0){
            for (int i=0;i<stockCount;i++){
                //左进右出
                redisTemplate.boundListOps(Constant.CONSTANT_SECKILLGOODS_ID_PRBFIX+id).leftPush(id);
            }
        }
    }

}
