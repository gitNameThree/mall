package com.mall.common.advice.response;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author 谢成伟
 * Date:2020/12/31
 * Time:15:32
 */
@Log4j2
@ControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice<Object> {

    private final static String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        final Class<?> clazz = methodParameter.getContainingClass();
        return !clazz.isAnnotationPresent(IgnoreResponseAdvice.class);

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 防止重复包裹的问题出现
        if (body instanceof UnityResult) {
            return body;
        }
        if (body instanceof String) {
            return JSON.toJSONString(UnityResult.success(body));
        }

        return UnityResult.success(body);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleException(Exception ex) {
        ex.printStackTrace();
        return UnityResult.error(ErrorException.RUNTIME_EXCEPTION.code, ex.getMessage());
    }
}
