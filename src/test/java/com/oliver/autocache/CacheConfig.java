package com.oliver.autocache;

import com.oliver.autocache.cache.CacheFactory;
import com.oliver.autocache.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Oliver
 * @time :2018/8/24.
 */
@Configuration
public class CacheConfig {

    @Bean
    CacheFactory cacheFactory(){
        return new CacheFactory(new CacheManager() {

            @Override
            public Object get(String key) {
                return null;
            }

            @Override
            public void put(Object obj, String key, int time) { }

            @Override
            public boolean contain(String key) {
                return false;
            }
        });
    }

}
