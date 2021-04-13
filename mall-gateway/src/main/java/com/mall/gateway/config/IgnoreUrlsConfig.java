package com.mall.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关白名单配置
 *
 * @author macro
 * @date 2020/6/17
 */
@RefreshScope
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IgnoreUrlsConfig {

    @Value("${ignoreUrlsConfig}")
    private String urls;
}
