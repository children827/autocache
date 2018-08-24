package com.oliver.autocache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author :Oliver
 * @time :2018/8/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InjectClazzTest {

    @Autowired
    private InjectClazz injectClazz;

    @Test
    public void show() throws Exception {
        injectClazz.show("入参1","入参2");
    }

}