package com.mall.common.enerty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:11:01
 * @ action  后台用户表映射类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String roleId;
    private String roleName;
}

