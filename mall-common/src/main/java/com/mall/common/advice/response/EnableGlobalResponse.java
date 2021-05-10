package com.mall.common.advice.response;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:14:45
 * @ action  请表明此文件的用途
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Import(GlobeResponseConfig.class)
public @interface EnableGlobalResponse {

}
