# autocache
利用注解实现自动缓存的工具类

被注解的方法会对结果自动使用缓存
调用目标方法时，系统会先从缓存中取结果，如果缓存中存在结果，则直接返回结果，
如果缓存中不存在结果，则继续运行目标方法生成结果存入缓存，同时返回。


1.工具依赖spring框架  
2.引入jar包后需要自己注入缓存实现：

```
        @Bean
	CacheFactory cacheFactory(){
		return new CacheFactory(new CacheManager() {

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
		});
	}
```


3.在需要实现缓存的方法上添加@Cache注解：  
  
```
@Controller     
public class InjectClazz {

    @Cache(baseKey = "test"，)
    public String show(@AsKey String s,@AsKey String s1){
        System.out.println("这里运行show");
        return "返回";
    }
}
```

4.缓存key生成策略：
      @Cache的baseKey属性作为缓存的基础key，在此基础上会再加上被@AsKey注解标注的参数，共同作为缓存的最终key
      如果未设置，会将被注释的类的签名作为baseKey的默认值
