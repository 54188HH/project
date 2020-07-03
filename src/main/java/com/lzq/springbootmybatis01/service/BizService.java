package com.lzq.springbootmybatis01.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: liuzhenqi
 * @create: 2020-07-03 09:51
 **/
@Service
public class BizService {
    @Autowired
    private BizUnitService service;
    private Map<Integer, Function<String, String>> checkResultDispatcherMuti = new HashMap<>();

    /**
     * 初始化 业务逻辑分派Map 其中value 存放的是 lambda表达式
     */
    @PostConstruct
    public void checkResultDispatcherMuitInit() {
        checkResultDispatcherMuti.put(1, order -> service.bizOne(order));
        checkResultDispatcherMuti.put(2, order -> service.bizTwo(order));
        checkResultDispatcherMuti.put(3, order -> service.bizThree(order));
    }

    public String getCheckResultMuti(String order, Integer level) {
        //写一段生成key的逻辑：
        //String ley = getDispatcherKey(order, level);
        //System.out.println("生成的key："+ley);
        Function<String, String> result = checkResultDispatcherMuti.get(level);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    /**
     * 判断条件方法
     */
    private String getDispatcherKey(String order, int level) {
        StringBuilder key = new StringBuilder("key");
        for (int i = 1; i <= level; i++) {
            key.append("_" + order + i);
        }
        return key.toString();
    }

}
