package com.oliver.autocache;

import com.oliver.autocache.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @author :Oliver
 * @time :2018/8/21.
 */
@Component
public class CacheHelperImpl implements CacheManager {
    @Override
    public Object get(String key) {
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
}
