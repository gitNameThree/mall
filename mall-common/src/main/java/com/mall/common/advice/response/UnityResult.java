package com.mall.common.advice.response;

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
public class UnityResult implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static UnityResult success(Object o) {
        return new UnityResult("200", "成功", o);
    }

    public static UnityResult error(String code, String msg) {
        return new UnityResult(code, msg, null);
    }


}
