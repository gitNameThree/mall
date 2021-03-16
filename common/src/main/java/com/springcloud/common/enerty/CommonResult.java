package com.springcloud.common.enerty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 谢成伟
 * Date:2020/12/31
 * Time:15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static CommonResult success(Object o,String msg) {
        return new CommonResult("200", msg, o);
    }

    public static CommonResult error(String code, String msg) {
        return new CommonResult(code, msg, null);
    }


}
