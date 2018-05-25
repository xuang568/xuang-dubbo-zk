package com.spring.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RateControlService {

    @Autowired
    private RedisMannager redisClient;

    private static String scriptLua =  "local times = redis.call('incr',KEYS[1])\n"
            +"if tonumber(times) == 1 then\n"
            +"redis.call('expire',KEYS[1],KEYS[2])\n"
            +"end\n"
            +"return times\n";

    public Boolean rateControlByRedisLua(String keyname, int threshold, int survival){
        Object result = redisClient.evalLua(scriptLua,keyname,String.valueOf(survival));
        try{
            int res = Integer.parseInt(result.toString());
            if (res > threshold){
                log.info("too many requests per second :"+ keyname + " : " + res);
                return false;
            }else {
                return true;
            }
        }catch (Exception e ){
            log.error("fail to evalsha to Lua in redis, cause:{}",e);
        }
        return false;
    }
}
