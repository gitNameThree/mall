package com.mall.auth.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢成伟
 * Date:2021/3/30
 * Time:13:53
 * @ action  请表明此文件的用途
 */
@Log4j2
@SpringBootTest
@RunWith(SpringRunner.class)
class UserAdminServiceImplTest {
    @Test
    public void testJwt() throws  NoSuchAlgorithmException {
        String keystore = "mall.keystore";
        //密钥库的密码
        String keystore_password = "123456";

        //密钥库文件路径
        ClassPathResource classPathResource = new ClassPathResource(keystore);
        //密钥别名
        String alias  = "key";
        //密钥的访问密码
        String key_password = "123456";
        //密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,keystore_password.toCharArray());
        //密钥对（公钥和私钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //jwt令牌的内容
        Map<String,String> body = new HashMap<>();
        body.put("name","weifeng");
        String bodyString = JSON.toJSONString(body);
        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
        //生成jwt令牌编码
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtLMHasdNffpHhODdlKKEGrGnWaRoR+RucO8IYftzvLr+V86jZQ6obn/yczhsqLIitoIw+d31II3U0VY2Pl30+q/RLXqDwExjWSLR+xm6SDnRNq+0mj2Q4ZpnResOJ0qyECJKNP6KzpgPLXtLpvjoCo/b78hDj+kUmGfjpVRbIo78WFqJfZOpbFY2iBcW3+e5UaquFZ0+Z9QjEysNOSUkz2ZARnhqmgOkW62WVVaFUEIHyPQrv7cEoGc8LTcndEy2HwrRaMa6qwId1dkEN2PnVnG+oZZLZTlx91anegSH/PHjO1Z4S5JGXHqBFba65R2lbF4kKmt+pp+5yqwdKR6VMwIDAQAB-----END PUBLIC KEY-----";
        Jwt test = JwtHelper.decodeAndVerify(encoded, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = test.getClaims();
        System.out.println(claims);

    }

}