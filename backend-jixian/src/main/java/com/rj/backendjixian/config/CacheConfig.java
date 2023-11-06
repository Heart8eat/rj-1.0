package com.rj.backendjixian.config;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public TimedCache<String,Object> getTimedCache(){
        long timeout=1000*60*10;
        //创建缓存，默认4毫秒过期
        TimedCache<String,Object> timedCache= CacheUtil.newTimedCache(timeout);
        long delay=1000*60*5;
        //启动定时任务，每5毫秒清理一次过期条目，注释此行首次启动仍会清理过期条目
        timedCache.schedulePrune(delay);
        return timedCache;
    }

}
