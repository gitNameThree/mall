package com.mall.auth.config;


import com.mall.auth.compant.JwtTokenEnhancer;
import com.mall.auth.service.impl.UserAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/26
 * Time:17:09
 * @ action  认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAdminServiceImpl userAdminService;

    @Autowired
    @Qualifier("JwtAccessTokenConverter")
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    JwtTokenEnhancer jwtTokenEnhacer;

    @Autowired
    @Qualifier("jwtTokenStore")
    TokenStore tokenStore;

    /**
     * 设置认证方式
     * client 认证的密钥
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("client"))
                .accessTokenValiditySeconds(60 * 60 * 24)
                .refreshTokenValiditySeconds(60 * 60 * 24)
                .scopes("all")
                //设置认证的方式
                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials");

    }

    /**
     * 怎么认证，认证后怎么返回信息
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        List<TokenEnhancer> list = new ArrayList<>();
        list.add(jwtTokenEnhacer);
        list.add(jwtAccessTokenConverter);
        chain.setTokenEnhancers(list);
        // 认证管理器
        endpoints.authenticationManager(authenticationManager)
                //认证逻辑
                .userDetailsService(userAdminService)
                //生成token的方式
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                //自定义返回jwt的负载
                .tokenEnhancer(chain);
    }

}
