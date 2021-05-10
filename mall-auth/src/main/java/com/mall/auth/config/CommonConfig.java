package com.mall.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 谢成伟
 * Date:2021/4/20
 * Time:9:24
 * @ action  请表明此文件的用途
 */
@Configuration
@MapperScan(basePackages = {"com.mall.auth.dao"})
public class CommonConfig {
}
