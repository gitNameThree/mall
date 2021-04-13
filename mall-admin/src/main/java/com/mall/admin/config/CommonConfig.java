package com.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:14
 * @ action  通用的配置文件
 */
@Configuration
@MapperScan(basePackages = {"com.mall.admin.dao"})
public class CommonConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
