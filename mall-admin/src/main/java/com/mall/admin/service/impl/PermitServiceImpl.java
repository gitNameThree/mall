package com.mall.admin.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.mall.admin.config.NacosConfig;
import com.mall.admin.contstant.Const;
import com.mall.admin.contstant.ConstEnum;
import com.mall.admin.dao.MenuDao;
import com.mall.admin.dao.RoleDao;
import com.mall.admin.enerty.db.Menu;
import com.mall.admin.enerty.db.Role;
import com.mall.admin.enerty.dto.LoginParams;
import com.mall.admin.enerty.vi.AuthToken;
import com.mall.admin.service.api.PermitService;
import com.mall.common.entity.UserInfo;
import com.mall.common.utils.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:22
 * @action 限相关的service层
 */
@Log4j2
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PermitServiceImpl implements PermitService {

    @Autowired
    MenuDao menuDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisService redisService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    NacosConfig nacosConfig;


    @Override
    public List<Menu> findMenuList() {
        // 获取当前登录的用信息
        UserInfo userInfo = userService.getUserInfo();
        Integer roleId = userInfo.getRoleId();
        String menuKey = ConstEnum.MENU_KEY + String.valueOf(roleId);
        //redis取菜单
//        String menuTree = (String) redisService.get(menuKey);
//        if (!StrUtil.isEmpty(menuTree)) {
//            return JSONObject.parseObject(menuTree, List.class);
//        }
        // 无缓存数据从数据库中拿
        List<Menu> resourceMenuList = menuDao.findMenuList(roleId);
        resourceMenuList.stream().forEach(menu -> {
            List<Menu> menuChildrenList = menu.getChildren();
            resourceMenuList.stream().forEach(children -> {
                if (Objects.equals(menu.getId(), children.getParentId())) {
                    menuChildrenList.add(children);
                }
            });
        });
        List<Menu> menuList =
                resourceMenuList.stream().filter(menu -> menu.getLevel() == 0).collect(Collectors.toList());
        //把菜单存到redis 中
//        redisService.set(menuKey, JSONObject.toJSONString(menuList), Const.TIME_OUT);
        return menuList;

    }

    @Override
    public List<Role> findRoleList() {
        return roleDao.selectList(null);
    }

    @Override
    public AuthToken applyForToken(LoginParams loginParams) {
        // 构造请求参数
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String httpbasic = httpBasic(nacosConfig.getClient(), nacosConfig.getClientSecret());
        headers.add("Authorization", httpbasic);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", loginParams.getUsername());
        body.add("password", loginParams.getPassword());
        HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity = new HttpEntity<>(body, headers);
        // nacos 获取服务的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose(Const.AUTH_SERVICE);
        URI uri = serviceInstance.getUri();
        String authUrl = uri + "/oauth/token";
        //申请令牌解析令牌
        // TODO 返回的类型是在springsecurity中的oauth2AccessToken类，这边先用map接
        ResponseEntity<Map> response =
                restTemplate.exchange(authUrl, HttpMethod.POST, multiValueMapHttpEntity, Map.class);
        Map oauth2Token = response.getBody();
        String token = MapUtil.getStr(oauth2Token, "access_token", null);
        AuthToken authToken = new AuthToken();
        if (oauth2Token.isEmpty() || StrUtil.isAllBlank(token)) {
            return authToken;
        }
        //redis保存令牌
        String userId = MapUtil.getStr(oauth2Token, "jti", "");
        String key = ConstEnum.KEY_HEAD.getCode() + userId;
        redisService.set(key, token, Const.LOGIN_TIME_OUT);
        authToken.setAccessToken(userId);
        authToken.setUsername(loginParams.getUsername());
        authToken.setTokenTimeout(Const.LOGIN_TIME_OUT);
        return authToken;
    }

    @Override
    public boolean loginOut() {
        UserInfo userInfo = userService.getUserInfo();
        String key = userInfo.getUserId();
        return redisService.del(key);
    }

    private String httpBasic(String clientId, String clientSecret) {
        //将客户端id和客户端密码拼接,按"客户端id,客户段密码"
        String string = clientId + ":" + clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());

        return "Basic " + new String(encode);
    }

}
