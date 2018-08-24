package com.oliver.autocache.Exception;

/**
 * 缓存异常类
 * @author :Oliver
 * @time :2018/8/22.
 */
public class CacheException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;


    public CacheException() {
        super();
    }


    public CacheException(String message) {
        super(message);
    }


    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }


    protected CacheException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
