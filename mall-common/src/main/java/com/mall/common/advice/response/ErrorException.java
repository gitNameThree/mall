package com.mall.common.advice.response;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * @author 谢成伟
 * Date:2020/12/31
 * Time:16:26
 */

public enum ErrorException {


    /**
     * 运行错误
     */
    RUNTIME_EXCEPTION("5","运行时异常");

    public String code;
    public String msg;

    ErrorException(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ErrorException getErrorExceptionByValue(String code){
        AtomicReference<ErrorException> atomicReference = new AtomicReference(null);

        Stream.of(ErrorException.values()).forEach(error->{
            if(Objects.equals(error.code,code)){
                atomicReference.set(error);
            }
        });
        return atomicReference.get();

    }
}
