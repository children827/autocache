package com.oliver.autocache.cache;

import com.oliver.autocache.Exception.CacheException;

/**
 * 缓存manager工厂类
 * @author :Oliver
 * @time :2018/8/24.
 */
public class CacheFactory {

    public CacheManager cacheManager = null;

    public CacheFactory() {
    }

    public CacheFactory(CacheManager cacheManager){
        setCacheManager(cacheManager);
    }

    /**
     * 设置缓存manager
     * @param cacheManager
     */
    public void setCacheManager(CacheManager cacheManager){
        this.cacheManager=cacheManager;
    }


    /**
     * 获取缓存manager
     * @return
     * @throws CacheException
     */
    public CacheManager getCacheManager() throws CacheException{
        if (null==cacheManager){
            throw new CacheException("请指定缓存实现！");
        }
        return cacheManager;
    }
}
