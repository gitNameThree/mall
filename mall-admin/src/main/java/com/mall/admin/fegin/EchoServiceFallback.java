package com.mall.admin.fegin;

import org.springframework.stereotype.Component;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:15:05
 * @ action  请表明此文件的用途
 */
public class EchoServiceFallback implements FeginService{
    @Override
    public String getUserInfo() {
        return "null";
    }
}
