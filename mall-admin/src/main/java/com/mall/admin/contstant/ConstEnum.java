package com.mall.admin.contstant;

/**
 * @author 谢成伟
 * Date:2021/4/2
 * Time:11:15
 * @ action  请表明此文件的用途
 */
public enum ConstEnum {
    TIME_OUT_SECOND("180", "超时时间"),
    KEY_HEAD("USER:", "保存到redis的请求头");

    private String code;
    private String message;

    ConstEnum(String code,String message){
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
