package com.mall.admin.contstant;

/**
 * @author 谢成伟
 * Date:2021/4/2
 * Time:11:15
 * @ action  请表明此文件的用途
 */
public enum IsDeleteEnum {
    DELETED("1", "已删除"),
    NOT_DELETED("0", "未删除");

    private String code;
    private String message;

    IsDeleteEnum(String code, String message){
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
