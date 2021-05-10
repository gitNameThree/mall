package com.mall.admin.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mall.admin.util.RestTemplateExceptionHandler;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
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
    @SentinelRestTemplate(fallback = "handleException", fallbackClass = RestTemplateExceptionHandler.class)
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡的规则
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new WeightedResponseTimeRule();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
