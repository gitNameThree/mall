package com.mall.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.mall.common.advice.response.UnityResult;
import com.mall.common.constant.AuthConstant;
import com.mall.common.utils.RedisService;
import com.mall.gateway.config.IgnoreUrlsConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MultiValueMap;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头
 *
 * @author macro
 * @date 2020/7/24
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //白名单路径移除JWT请求头
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> ignoreUrls = Arrays.asList(StringUtils.split(ignoreUrlsConfig.getUrls(), ","));
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        // 验证cookie是否有用户的唯一的标识
        MultiValueMap<String, HttpCookie> cookieMultiValueMap = request.getCookies();
        HttpCookie httpCookie = cookieMultiValueMap.getFirst("jti");
        ServerHttpResponse response = exchange.getResponse();
        if (httpCookie == null) {
            return response.writeWith(Mono.error(new Throwable("无法识别用户信息，请重新登录")));
        }
        String jti = httpCookie.getValue();
        if (StrUtil.isBlank(jti)) {
            return response.writeWith(Mono.error(new Throwable("无法识别用户信息，请重新登录")));
        }
        String token = (String) redisService.get(jti);
        if (StrUtil.isBlank(token)) {
            return response.writeWith(Mono.error(new Throwable("登录已过期请重新登录，请重新登录")));
        }
        ServerHttpRequest serverHttpRequest = exchange.getRequest()
                .mutate()
                .header(AuthConstant.JWT_TOKEN_HEADER, AuthConstant.JWT_TOKEN_PREFIX + token)
                .build();
        exchange = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(exchange);
    }
}
