package com.mall.admin.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author 谢成伟
 * Date:2021/4/1
 * Time:11:08
 * @ action  请表明此文件的用途
 */
@RefreshScope
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NacosConfig {

    @Value("${client}")
    private String client;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${publicKey}")
    private String publicKey;
}
