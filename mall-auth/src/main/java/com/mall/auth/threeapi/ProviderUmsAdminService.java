package com.mall.auth.threeapi;

import com.mall.common.advice.response.UnityResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:14:02
 * @ action  调用后台的服务
 */
@Component
@FeignClient("mall-admin")
public interface ProviderUmsAdminService {


    /**
     *  调用auth-admin 获取用户
     * @param userName 用户名
     * @return
     */
    @PostMapping("/getUmsAdminByUserName")
    UnityResult getUmsAdminByUserName(@RequestParam("userName") String userName);

}
