package com.me.testjpa.jpa.config;

import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther:
 * @Description:
 * @Date: 2019/11/28 11:45
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    // 获取 ApplicationContext 容器
    private static ApplicationContext myApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        myApplicationContext = applicationContext;
    }

    /**
     * 通过名称获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return myApplicationContext.getBean(beanName);
    }

    /**
     * 通过类型获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return myApplicationContext.getBean(clazz);
    }

    /**
     * 通过名称和类型获取 bean
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> clazz){
        return myApplicationContext.getBean(beanName, clazz);
    }
}
