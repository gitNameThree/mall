package com.mall.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.mall.common.constant.AuthConstant;
import com.mall.common.utils.RedisService;
import com.nimbusds.jose.JWSObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @author 谢成伟
 * Date:2021/4/7
 * Time:9:12
 * @ action  全局过滤类
 */
@Log4j2
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    RedisService redisService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        String userId = exchange.getRequest().getHeaders().getFirst(AuthConstant.USER_TOKEN_HEADER);

        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter.filter() user:{}", userStr);
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header(AuthConstant.USER_TOKEN_HEADER, userStr)
                    .header(AuthConstant.USER_ID_TOKEN_HEADER,userId)
                    .build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 1;
    }


}
