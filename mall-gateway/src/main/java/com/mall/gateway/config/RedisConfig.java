package com.mall.gateway.config;

import com.mall.common.config.BaseRedisConfig;
import com.mall.gateway.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
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
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter();
    }
}
