package com.mall.common.enerty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:15:34
 * @ action  放回的状态码
 */

public enum ResultCodeEnum {
    SUCCESS_CODE("200", "请求成功");

    private String code;
    private String message;

    ResultCodeEnum(String code,String message){
        this.code = code;
        this.message = message;

    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }


}
