package com.oliver.autocache;

import com.oliver.autocache.cache.CacheHelper;
import org.springframework.stereotype.Component;

/**
 * @author :Oliver
 * @time :2018/8/21.
 */
@Component
public class CacheHelperImlp implements CacheHelper {
    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public boolean put(Object obj, String key, long time) {
        return false;
    }

    @Override
    public boolean contain(String key) {
        return false;
    }
}
