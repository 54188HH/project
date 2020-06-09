package com.lzq.springbootmybatis01.thread.impl;

import com.lzq.springbootmybatis01.entity.Goods;
import com.lzq.springbootmybatis01.entity.OrderRecord;
import com.lzq.springbootmybatis01.thread.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncServiceImpl implements AsyncService {

  private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  @Async("asyncServiceExecutor")
  public void executeAsync() {

    logger.info("start executeAsync");

    try {

      OrderRecord orderRecord = (OrderRecord) redisTemplate.boundListOps(OrderRecord.class.getSimpleName()).rightPop();
      if (null != orderRecord){
        System.out.println("商品id：："+orderRecord.getId());
        Goods gg = (Goods) redisTemplate.boundHashOps(Goods.class.getSimpleName()).get(orderRecord.getId());
           //生产订单表插入数据库或者redis
          //这里选择插入到redis里面
          //redisTemplate.boundHashOps(订单实体类.class.getSimpleName()).put(orderRecord.getUserId(),订单实体类);
          System.out.println("库存：："+gg.toString());
          synchronized (AsyncServiceImpl.class){
              //获取最新的商品信息
              gg = (Goods) redisTemplate.boundHashOps(Goods.class.getSimpleName()).get(orderRecord.getId());

              //库存 减 1
              gg.setStock(gg.getStock()-1);
              System.out.println("商品剩余库存"+gg.getStock());
              //判断库存是否<=0
              if (gg.getStock()<=0){
                  //如果小于等于0 更新数据库  删除秒杀商品的redis缓存
                  //更新数据库
                  redisTemplate.boundHashOps(Goods.class.getSimpleName()).delete(orderRecord.getId());
              }else{
                  //如果仍=然有 库存，将减1后的库存更新到redis缓存
                  redisTemplate.boundHashOps(Goods.class.getSimpleName()).put(orderRecord.getId(),gg);
              }
          }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    logger.info("end executeAsync");
  }
}
