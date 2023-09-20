package xiaoliu.life.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public String stringGet(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void stringSet(String key,String value,long timeout){
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }

    public Set<String> setGet(String key){
        return redisTemplate.boundSetOps(key).members();
    }

    public void setSet(String key,Integer value){
        redisTemplate.boundSetOps(key).add(String.valueOf(value));
    }

    public void setDelete(String key,String value){
        redisTemplate.boundSetOps(key).remove(value);
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }

    public boolean exist(String key){
        return redisTemplate.hasKey(key);
    }
}
