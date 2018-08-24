package com.oliver.autocache.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 作为标记，被标记的参数会被当做缓存key的一部分
 * @author :Oliver
 * @time :2018/8/21.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface AsKey {
}
