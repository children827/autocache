package com.oliver.autocache.aop;

import com.oliver.autocache.Exception.CacheException;
import com.oliver.autocache.annotation.AsKey;
import com.oliver.autocache.annotation.Cache;
import com.oliver.autocache.cache.CacheFactory;
import com.oliver.autocache.cache.CacheManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * aspect的切面增强类
 *
 * @author :Oliver
 * @time :2018/8/20.
 */
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private CacheFactory cacheFactory;


    /**
     * 切入点
     */
    @Pointcut("@annotation(com.oliver.autocache.annotation.Cache)")
    public void cache() {
    }

    @Around("cache()")
    public Object doAroundCache(ProceedingJoinPoint pjp) throws Throwable {
        CacheManager cacheHelper = cacheFactory.getCacheManager();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Object[] totalArgs = pjp.getArgs();
        Method method = signature.getMethod();

        Cache cache = method.getAnnotation(Cache.class);

        int time = cache.time();

        String baseKey = cache.baseKey();
        //缓存key
        String key = genKey(totalArgs, method, baseKey);

        //如果condition注解标记的参数为false，则不使用换成，直接运行返回（同时刷新缓存中的结果）
        if (genCondition(totalArgs, method)) {
            //缓存处理
            try {
                if (cacheHelper.contain(key)) {
                    return cacheHelper.get(key);
                }
            } catch (Exception e) {
                //选择catch而不抛出的考虑是因为缓存属于附属功能，不应该影响正常业务程序的调用
                e.printStackTrace();
                //throw new CacheException("缓存获取出错！", e);
            }
        }


        Object res = null;
        synchronized (pjp.getClass()) {

            //同步方法内再次检查缓存，防止多个线程同时调用
            try {

                res = cacheHelper.get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (null != res) {
                return res;
            }

            //运行目标方法
            try {
                res = pjp.proceed();
            } catch (Throwable throwable) {
                throw new CacheException("方法调用出错！", throwable);
            }

            //结果处理
            if (null == res) {
                return res;
            }
            try {
                cacheHelper.put(res, key, time);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CacheException("缓存存储出错！", e);
            }
        }
        return res;
    }


    private boolean genCondition(Object[] totalArgs, Method method) throws Exception {
        List<Object> args = getParamValueByAnnotation(method, totalArgs, AsKey.class);
        if (null != args && args.size() != 0 && args.get(0) instanceof Boolean) {
            return (Boolean) args.get(0);
        }
        return true;
    }


    /**
     * 生成作为缓存的key
     * 生成策略：
     * 如果@Cache注解设置baseKey属性不为""，则将baseKey与本方法中被@AsKey标记的参数(只能是基本数据类型)拼接，
     * 拼接形式为“baseKey_参数1_参数2...”；
     * 如果@Cache注解设置baseKey属性为""，则将方法签名与本方法中被@AsKey标记的参数(只能是基本数据类型)拼接，
     * 拼接形式为“方法签名_参数1_参数2...”；
     *
     * @param totalArgs
     * @param method
     * @param baseKey
     * @return
     * @throws Exception
     */
    private String genKey(Object[] totalArgs, Method method, String baseKey) throws Exception {
        //获取方法中被@AsKey注解的参数值并拼接
        List<Object> args = getParamValueByAnnotation(method, totalArgs, AsKey.class);
        String argKey = "";
        for (Object s : args) {
            try {
                argKey += "_".concat(String.valueOf(s));
            } catch (Exception e) {
                throw new CacheException("@AsKey注解只能使用基本数据类型！", e);
            }
        }

        //如果未设置baseKey属性，那将整个方法签名作为默认的baseKey
        if (null == baseKey || "".equals(baseKey)) {
            baseKey = method.toString();
        }

        //最终的缓存key
        String key = baseKey.concat(argKey);
        if (null == key || "".equals(key)) {
            throw new CacheException("缓存key不能为空，请设置baseKey属性和@AsKey注解");
        }
        return key;
    }

    /**
     * 获取被注解的参数的值
     *
     * @param method 方法
     * @param params 所有参数列表
     * @param clazz  参数注解
     * @return
     * @throws Exception
     */
    private List<Object> getParamValueByAnnotation(Method method, Object[] params, Class<?> clazz) throws Exception {
        List<Object> result = new ArrayList<>();
        Annotation[][] ans = method.getParameterAnnotations();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                if (clazz.isInstance(ans[i][j])) {
                    result.add(params[i]);
                }
            }
        }
        return result;
    }

}
