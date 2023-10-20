package jgdabc.yingli.util;

import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCommon {

    @Autowired
    private RedisTemplate redisTemplate;



    public void setVal(String key,String val,long timeout,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,val,timeout,timeUnit);
    }






    public Boolean removeValue(String key) {
      return    redisTemplate.delete(key);
    }

    public Boolean tokenFlush(String key,int tokenExpireTime) {
           return redisTemplate.expire(key,tokenExpireTime, TimeUnit.MINUTES);
    }

    public Object getValue(String key)
    {
        return redisTemplate.opsForValue().get(key);

    }


    public void setZSetValue(String key,String value,double score ){
        redisTemplate.opsForZSet().add(key,value,score);
    }

    public Set getZetValueOfRange(String key,Long start,Long end)
    {
      return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);


    }



        public void setHashValue(String key,String hashKey,Object value)
    {
       redisTemplate.opsForHash().put(key,hashKey,value);
    }

    public Object getHashValue(String key,String hashKey) {
        return  redisTemplate.opsForHash().get(key,hashKey);
    }
}
