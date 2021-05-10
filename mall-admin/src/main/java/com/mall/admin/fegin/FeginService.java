package com.mall.admin.fegin;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mall.admin.config.FeginConfig;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:14:50
 * @ action  请表明此文件的用途
 */
@FeignClient(name = "mall-auth")
public interface FeginService {
    @GetMapping("/oauth/user")
    public String getUserInfo();
}
