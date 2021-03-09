package com.oliver.autocache;

import com.oliver.autocache.cache.CacheFactory;
import com.oliver.autocache.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author :Oliver
 * @time :2018/8/24.
 */
@Configuration
@EnableAspectJAutoProxy
public class CacheConfig {

    @Bean
    CacheFactory cacheFactory(){
        return new CacheFactory(new CacheManager() {

            @Override
            public Object get(String key) {
                System.out.println("获取缓存：");
                return null;
            }

            @Override
            public void put(Object obj, String key, int time) {
                System.out.println("存入缓存："+obj + key + time);
            }

            @Override
            public boolean contain(String key) {
                return false;
            }
        });
    }

}
