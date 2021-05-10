package com.mall.admin.config;

import com.mall.admin.fegin.EchoServiceFallback;
import org.springframework.context.annotation.Bean;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:15:09
 * @ action  请表明此文件的用途
 */
public class FeginConfig {
    @Bean
    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}
