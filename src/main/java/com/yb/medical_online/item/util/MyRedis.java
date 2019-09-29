package com.yb.medical_online.item.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyRedis {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public void set(final String key,Object value) throws Exception{
        redisTemplate.opsForValue().set(key,value);
    }


    /**
     * 更新缓存
     * @param key
     * @param value
     * @return
     */
    public Object getAndSet(final String key, Object value) throws Exception{
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     * @throws Exception
     */
    public boolean delete(final String key) throws Exception{
        return redisTemplate.delete(key);
    }

}
