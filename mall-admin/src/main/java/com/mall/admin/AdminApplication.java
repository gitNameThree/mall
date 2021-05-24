package com.mall.admin;

import com.mall.common.advice.response.EnableGlobalResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author ASUS
 */

@EnableFeignClients
@EnableGlobalResponse
@SpringBootApplication
@EnableRetry
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}