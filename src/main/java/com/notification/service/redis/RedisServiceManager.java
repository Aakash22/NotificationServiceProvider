package com.notification.service.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Aakash on 19/06/17.
 */
public class RedisServiceManager {

    private JedisPool redisPool;

    public RedisServiceManager(String host,Integer redisPort){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);

        redisPool = new JedisPool(poolConfig,host,redisPort);
    }

    public Long lpush(String key,String value){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            return redis.lpush(key,value);
        }
        finally {
            if(redis != null){
                redisPool.returnResource(redis);
            }
        }
    }

    public String rpop(String key){
        Jedis redis = null;
        try{
            redis = redisPool.getResource();
            return redis.rpop(key);
        }
        finally {
            if(redis != null){
                redisPool.returnResource(redis);
            }
        }
    }
}
