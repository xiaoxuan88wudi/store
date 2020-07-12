package com.imooc.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class SimpleRateLimter {
    private Jedis jedis;

    public SimpleRateLimter(Jedis jedis) {
        this.jedis = jedis;
    }
    public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount){
        String key = String.format("hist:%s:%s",userId,actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zadd(key,nowTs,""+nowTs);
        pipe.zremrangeByScore(key,0,nowTs-period*1000);
        Response<Long> count = pipe.zcard(key);
        pipe.expire(key,period+1);
        pipe.exec();
        pipe.close();
        return count.get()<maxCount;
    }
}
