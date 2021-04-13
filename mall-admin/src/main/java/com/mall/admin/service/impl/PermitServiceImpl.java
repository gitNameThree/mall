package com.mall.admin.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.mall.admin.dao.MenuDao;
import com.mall.admin.contstant.ConstEnum;
import com.mall.admin.enerty.db.Menu;
import com.mall.admin.enerty.vi.AuthToken;
import com.mall.admin.service.api.PermitService;
import com.mall.common.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:22
 * @action 限相关的service层
 */
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class PermitServiceImpl implements PermitService {

    @Resource
    MenuDao menuDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisService redisService;


    @Override
    public List<Menu> findMenuList() {
        List<Menu> menuList = menuDao.findMenuList();
        menuList.stream().forEach(menu -> {
            List<Menu> menuChildrenList = menu.getChildren();
            menuList.stream().forEach(children -> {
                if (Objects.equals(menu.getId(), children.getParentId())) {
                    menuChildrenList.add(children);
                }
            });
        });
        return menuList.stream().filter(menu -> menu.getLevel() == 0).collect(Collectors.toList());

    }

    @Override
    public List<Menu> findFirstMenuList() {

        return null;
    }

    @Override
    public AuthToken applyForToken(String url, HttpEntity<MultiValueMap<String, Object>> multiValueMapHttpEntity) {
        AuthToken authToken = new AuthToken();
        // 认证服务器申请令牌
        // TODO 返回的类型是在springsecurity中的oauth2AccessToken类，这边先用map接
        ResponseEntity<Map> response =
                restTemplate.exchange(url, HttpMethod.POST, multiValueMapHttpEntity, Map.class);
        Map oauth2Token = response.getBody();
        String token = MapUtil.getStr(oauth2Token, "access_token", null);
        if (oauth2Token.isEmpty() || StrUtil.isAllBlank(token)) {
            return authToken;
        }
        //redis保存令牌
        String key = ConstEnum.KEY_HEAD.getCode() + MapUtil.getStr(oauth2Token, "jti", "");
        int timeOut = Integer.parseInt(ConstEnum.TIME_OUT_SECOND.getCode());
        redisService.set(key, token);
        authToken.setAccessToken(key);
        authToken.setTokenTime(timeOut);
        return authToken;
    }

}
