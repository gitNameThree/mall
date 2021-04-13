package com.mall.auth.compant;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.mall.auth.enerty.UserDetailImpl;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 *
 * @author xiecw
 * @date 2020/6/19
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserDetailImpl userDetail  = (UserDetailImpl) authentication.getPrincipal();
        Map<String, Object> info = BeanUtil.beanToMap(userDetail);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
