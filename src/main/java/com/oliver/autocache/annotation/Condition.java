package com.oliver.autocache.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 参数标记方法，被标记的参数值如果为false，则不使用换成，直接调用方法返回(同时刷新缓存中的内容)
 * 每个方法中只能有一个@condition注解
 * 如果方法参数中未加此注解，或者注解参数不属于boolean类型，则默认使用缓存
 * @author :Oliver
 * @time :2020\4\17 0017.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Condition {
}
