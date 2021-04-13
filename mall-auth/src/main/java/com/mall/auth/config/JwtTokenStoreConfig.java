package com.mall.auth.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author 谢成伟
 * Date:2021/3/30
 * Time:14:11
 * @ action  请表明此文件的用途
 */
@Log4j2
@Configuration
public class JwtTokenStoreConfig {

    @Autowired
    NacosConfig nacosConfig;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(JwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter JwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(getPrivateKey());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair getPrivateKey () {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("mall.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("mallkey", "123456".toCharArray());
    }

}
