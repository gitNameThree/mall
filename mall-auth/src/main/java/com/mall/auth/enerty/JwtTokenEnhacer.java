//package com.mall.auth.enerty;
//
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author 谢成伟
// * Date:2021/3/26
// * Time:16:50
// * @ action  申请令牌返回的对象
// */
//public class JwtTokenEnhacer implements TokenEnhancer {
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("aaaa", "info");
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
//        return accessToken;
//    }
//}
