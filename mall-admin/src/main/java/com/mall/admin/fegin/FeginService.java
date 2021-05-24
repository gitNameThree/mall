package com.mall.admin.fegin;

import com.mall.admin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:14:50
 * @ action  请表明此文件的用途
 */
@FeignClient(name = "mall-auth", fallback = EchoServiceFallback.class, configuration = FeginConfig.class)
public interface FeginService {
    /**
     * 调用认证中心的接口
     * @return
     */
    @GetMapping("/oauth/user")
    public String getUserInfo();
}
