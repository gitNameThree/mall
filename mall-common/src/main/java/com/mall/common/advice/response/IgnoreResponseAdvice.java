package com.mall.common.advice.response;

import java.lang.annotation.*;

/**
 * @author 谢成伟
 * Date:2021/1/14
 * Time:14:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface IgnoreResponseAdvice {
}
