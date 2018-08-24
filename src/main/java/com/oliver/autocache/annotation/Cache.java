package com.oliver.autocache.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 被注解的方法会对结果自动使用缓存
 * 调用目标方法时，系统会先从缓存中取结果，如果缓存中存在结果，则直接返回结果，
 * 如果缓存中不存在结果，则继续运行目标方法生成结果存入缓存，同时返回。
 *
 * @author :Oliver
 * @time :2018/8/20.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Cache {

    /**
     * 缓存的基础key，在此基础上会再加上被@AsKey注解标注的参数，共同作为缓存的key
     * 如果未设置，会将被注释的类的签名作为baseKey的默认值
     *
     * @return
     */
    String baseKey() default "";

    /**
     * 缓存时间，默认为30s，参考{CacheTime},也可以自定义
     *
     * @return
     */
    long time() default CacheTime.TIME_MIDDLE;


}
