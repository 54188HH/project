package com.lzq.springbootmybatis01.controller;

import com.lzq.springbootmybatis01.entity.Goods;
import com.lzq.springbootmybatis01.constant.Constant;
import com.lzq.springbootmybatis01.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
/**
* @Description: 秒杀商品
* @Param:
* @return:
* @Author: liuzhenqi
* @Date: 2020-5-20
*/
public class SeckillController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/seckill")
    public String seckillGoods(Integer id,String userId){
        return  seckillService.saveOrder(id,userId);
    }
    @RequestMapping("/saveOrder")
    public String saveOrder(Long id){
        List<Goods> list = new ArrayList<Goods>();
        Goods goods = new Goods();
        goods.setGoodsName("1+手机");
        goods.setPrice(new BigDecimal(3500.00));
        goods.setStock(3);
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
            redisTemplate.boundHashOps(Goods.class.getSimpleName()).put(g1.getId(),g1);
            //为每个商品创建一个队列，队列中放和库存量相同数量的商品id
            createQueue(g1.getId(),g1.getStock());
        }
        return null;
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
