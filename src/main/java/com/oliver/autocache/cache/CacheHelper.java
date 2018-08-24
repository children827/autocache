package com.oliver.autocache.cache;

/**
 * 缓存实现方式的接口
 * @author :Oliver
 * @time :2018/8/20.
 */
public interface CacheHelper {

    /**
     * 读取缓存
     * @param key 缓存的key
     */
    Object get(String key);

    /**
     * 存入缓存
     * @param obj 需要缓存的对象
     * @param key 缓存的key
     * @param time 缓存时间，单位s
     * @return
     */
    boolean put(Object obj,String key,long time);

    /**
     * 判断缓存中是否存在相应的对象
     * @param key
     * @return
     */
    boolean contain(String key);

}
