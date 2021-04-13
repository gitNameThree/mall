package com.mall.admin.enerty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/4/13
 * Time:10:54
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String roleId;
    private String roleName;
}
