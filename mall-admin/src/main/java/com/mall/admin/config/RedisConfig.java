package com.mall.admin.config;

import com.mall.common.config.BaseRedisConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 谢成伟
 * Date:2021/4/2
 * Time:10:27
 * @ action  redis 配置
 */
@Configuration
public class RedisConfig extends BaseRedisConfig {

    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.43.211:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;

    }


}
