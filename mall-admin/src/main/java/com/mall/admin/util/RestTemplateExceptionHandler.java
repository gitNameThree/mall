package com.mall.admin.util;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.mall.common.advice.response.UnityResult;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:14:01
 * @ action  RestTemplate 服务降级、熔断
 */
public class RestTemplateExceptionHandler {
    public static ClientHttpResponse handleException(HttpRequest request, byte[] body,
                                                     ClientHttpRequestExecution execution, BlockException exception) {
        UnityResult unityResult = UnityResult.error("408","服务调用异常");
        return  new SentinelClientHttpResponse(JSONObject.toJSONString(unityResult));

    }

}
