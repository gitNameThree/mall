package com.mall.admin.controller;

import com.mall.admin.config.NacosConfig;
import com.mall.admin.enerty.vi.AuthToken;
import com.mall.admin.service.api.PermitService;
import com.mall.admin.service.api.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/18
 * Time:9:54
 * @action 用作登录的作用
 */
@Log4j2
@RestController
@RequestMapping("/permit")
public class PermitController {

    @Autowired
    PermitService permitService;

    @Autowired
    UserService userService;

    @Autowired
    NacosConfig nacosConfig;

    @GetMapping("/findMenuList")
    private List findMenuList() {
        userService.getUserInfo();
        return permitService.findMenuList();
    }

    @GetMapping("/findFirstMenuList")
    private List findFirstMenus() {
        return permitService.findMenuList();
    }

    @PostMapping("/login")
    private AuthToken login(@RequestParam("username") String userName, @RequestParam("password") String password) {
        // 认证服务器申请令牌
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String httpbasic = httpBasic(nacosConfig.getClient(), nacosConfig.getClientSecret());
        headers.add("Authorization", httpbasic);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", userName);
        body.add("password", password);
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = new HttpEntity<>(body, headers);
        String authUrl = "http://localhost:9093/oauth/token";
        return permitService.applyForToken(authUrl, multiValueMapHttpEntity);

    }

    private String httpBasic(String clientId, String clientSecret) {
        //将客户端id和客户端密码拼接,按"客户端id,客户段密码"
        String string = clientId + ":" + clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());

        return "Basic " + new String(encode);
    }


}
