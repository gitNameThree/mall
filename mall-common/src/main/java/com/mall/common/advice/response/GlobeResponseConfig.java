package com.mall.common.advice.response;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:14:38
 * @ action  统一的返回参数配置
 */
@Configuration
public class GlobeResponseConfig {
    @Bean
    public  CommonResponseAdvice getCommonResponseAdvice(){
        return new CommonResponseAdvice();
    }
}
