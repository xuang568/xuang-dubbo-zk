package com.xuang.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisMannager {
    @Autowired(required = true)
    protected StringRedisTemplate stringRedisTemplate ;

    RedisSerializer<String> stringRedisSerializer=new StringRedisSerializer();

    private static String scriptLua =  "local times = redis.call('incr',KEYS[1])\n"
            +"if tonumber(times) == 1 then\n"
            +"redis.call('expire',KEYS[1],KEYS[2])\n"
            +"end\n"
            +"return times\n";

    /**
     * 设置redisTemplate
     * @param stringRedisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    public RedisSerializer<String> getRedisSerializer() {
        return stringRedisTemplate.getStringSerializer();
    }

    public String getName(){
        stringRedisTemplate.opsForValue().set("name","xuang");
        Object name = stringRedisTemplate.opsForValue().get("name");
        return String.valueOf(name);
    }

    public Boolean rateControlByRedisLua(String keyname, int threshold, int survival){
        Object result = evalLua(scriptLua,keyname,survival);
        try{
            int res = Integer.parseInt(result.toString());
            if (res > threshold){
                System.out.println("too many requests per second :"+res);
                return false;
            }else {
                System.out.println("requests per second :"+res);
                return true;
            }
        }catch (Exception e ){
            System.out.println(e);
            return  true;
        }
    }

    public Object evalLua(String scriptLua, String keyname, int s) {
        DefaultRedisScript<String> redisScript = getRedisScript();

        List<String> list=new ArrayList();
        list.add(keyname);

      return  stringRedisTemplate.execute(redisScript,stringRedisSerializer,stringRedisSerializer,list,String.valueOf(s));
    }

    private DefaultRedisScript<String> getRedisScript() {
        DefaultRedisScript redisScript=new DefaultRedisScript();
        redisScript.setScriptText(scriptLua);
        redisScript.setResultType(String.class);
        return redisScript;

    }
}
