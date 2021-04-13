package com.mall.gateway.config;

import com.mall.gateway.authorization.AuthorizationManager;
import com.mall.gateway.component.RestAuthenticationEntryPoint;
import com.mall.gateway.component.RestfulAccessDeniedHandler;
import com.mall.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * 资源服务器配置
 *
 * @author macro
 * @date 2020/6/19
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws MalformedURLException {
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        http.oauth2ResourceServer()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .jwt().publicKey((RSAPublicKey) getPrivateKey());
        http.authorizeExchange()
                //白名单配置
                .pathMatchers(StringUtils.split(ignoreUrlsConfig.getUrls(), ",")).permitAll()
                //鉴权管理器配置
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                //处理未授权
                .accessDeniedHandler(restfulAccessDeniedHandler)
                //处理未认证
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    @Bean
    public PublicKey getPrivateKey()  {
        String pubStr ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkfuxy/o0MizFItgPpfhsC2a5FlBHQLUCdmcvG"
                + "kUcIEMx5d2u8qgWy/q+zWdvLsolLXurl1OXpvOXe6kWkRQqxFwtAPa4beKknz9MQ7LTytpRc63J/V4eIq7fp0LKVzRTxJ"
                + "M8zCsgI8BmOop4foYhxb91xyUpeDvUOmbQZF4+/KjrvJ1ilvZt2A25e0uTpRp2I5Qy0yxxkIjB2t0gGksgUbj0dtUVS7c"
                + "6ZkpenpYiZDT0kZJyVPJoZlUr2irERXJtrLaLIvS/zEnPNh0VEQlMyvC6zoRYqLgGa9QEysfjoRHkL+BqzyZtBbvuuwhdDwfhMF"
                + "lycGBclASvJRmFOLurQwIDAQAB";
        ByteBuffer byteBuffer =
                ByteBuffer.wrap(Base64.getDecoder().decode(pubStr));
        byte[] keyBytes = byteBuffer.array();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=null;
        PublicKey publicKey=null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        }catch (Exception e){
            e.printStackTrace();
        }

        return publicKey;
    }

}
