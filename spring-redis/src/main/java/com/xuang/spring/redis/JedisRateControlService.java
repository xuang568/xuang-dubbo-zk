package com.xuang.spring.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class JedisRateControlService {

    private static RedisMannager redisMannager =new RedisMannager();

    private static String scriptLua =  "local times = redis.call('incr',KEYS[1])\n"
            +"if tonumber(times) == 1 then\n"
            +"redis.call('expire',KEYS[1],KEYS[2])\n"
            +"end\n"
            +"return times\n";

    public Boolean rateControlByRedisLua(String keyname, int threshold, int survival){
        Object result = redisMannager.evalLua(scriptLua,keyname,survival);
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
}
