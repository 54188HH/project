package com.lzq.springbootmybatis01.util;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @program: springboot-mybatis01
 * @description:
 * @author: lzq
 * @create: 2020-05-10 17:57
 **/
public class BeanUtils implements ApplicationContextAware {

    private  static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    /**
    * @Title: BeanUtils
    * @Description: (把继承该类的 类成员变量  通过spring 管理的Bean 注入到继承的类中去)
    * @author lzq
    * @params
    * @return
    * @date 2020/5/10
    * @version V1.0
    */
    public BeanUtils(){
        //加载继承 该类的类 扫描成员变量
        Reflections reflections = new Reflections(this.getClass(), new FieldAnnotationsScanner());

        //将所有含有Resource注解的成员变量扫描出来
        Set<Field> fields = reflections.getFieldsAnnotatedWith(javax.annotation.Resource.class);

        //循环遍历成员变量
        for (Field f:fields){
            try {
                //获的成员变量的类名
                String simpleName = f.getType().getSimpleName();
                //因为我们的spring里面管理的bean的name 都是首字母小写的  所以我们需要把首字母转为小写
                String beanName = toLowerCaseforFirst(simpleName);
                Object bean = applicationContext.getBean(beanName);
                if (bean == null){
                    return;
                }
                //打破封装
                f.setAccessible(true);
                //把spring管理的对象 设置到我们反射出来的对象中
                f.set(this,bean);
            }catch (Exception e){

            }
        }
    }
    private String toLowerCaseforFirst(String simpleName){
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
