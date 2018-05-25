package com.spring.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.ArrayList;
import java.util.List;

public class RedisMannager {

    private static RedisTemplate redisTemplate =new RedisTemplate();

    /**
     * StringRedis模板
     */
    @Autowired(required = false)
   private static StringRedisTemplate stringRedisTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-redis.xml");
        final RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate",RedisTemplate.class);
        redisTemplate.opsForValue().set("name","xuang");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name+"");

    }

    public Object evalLua(String scriptLua, String keyname, String s) {

                List list=new ArrayList();
                list.add(keyname);
                DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
                defaultRedisScript.setScriptText(scriptLua);
                defaultRedisScript.setResultType(java.lang.String.class);


        return null;

        }
}
