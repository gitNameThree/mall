package com.mall.common.advice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 谢成伟
 * Date:2020/12/31
 * Time:15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnityResult implements Serializable {
    private String status;
    private String message;
    private Date time;
    private Object data;

    public static UnityResult success(Object o) {
        return new UnityResult("200", "请求成功", new Date(), o);
    }

    public static UnityResult error(String code, String msg) {
        return new UnityResult(code, msg, new Date(), null);
    }
}
