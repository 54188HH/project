package com.lzq.springbootmybatis01.util;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-10 17:06
 **/
public class StrategryFactory {
    private static  StrategryFactory factory = new StrategryFactory();
    public static HashMap<Integer,String> source_map = new HashMap<>();
    static {
        //根据注解分析出来，放到map里面 kay就是注解的值
        //value 就是   com.lzq.springbootmybatis01.util.
        Reflections reflections = new Reflections("com.lzq.springbootmybatis01.util");
        Set<Class<?>> classList= reflections.getTypesAnnotatedWith(Type.class);
        //获取用到@Type注解的类，放到map中去
        for(Class clazz:classList ){
            Type t = (Type) clazz.getAnnotation(Type.class);
            source_map.put(t.value(),clazz.getCanonicalName());
        }
    }
    public  Strategry create(int channlId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //根据map中取出   com.lzq.springbootmybatis01.util.
        String clazzPath = source_map.get(channlId);
        Class clazz=Class.forName(clazzPath);
        return (Strategry)clazz.newInstance();
    }
    public static  StrategryFactory getInstance(){
        return  factory;
    }

}
