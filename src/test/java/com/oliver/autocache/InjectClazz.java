package com.oliver.autocache;

import com.oliver.autocache.annotation.AsKey;
import com.oliver.autocache.annotation.Cache;
import com.oliver.autocache.annotation.CacheTime;
import org.springframework.stereotype.Controller;

/**
 * @author :Oliver
 * @time :2018/8/20.
 */
@Controller
public class InjectClazz {

    @Cache(baseKey = "",time = CacheTime.TIME_SHORT)
    public String show(@AsKey String s,@AsKey String s1){
        System.out.println("这里显示");
        return "返回";
    }
}
