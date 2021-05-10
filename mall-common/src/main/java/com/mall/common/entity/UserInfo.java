package com.mall.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 谢成伟
 * Date:2021/4/13
 * Time:10:54
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable{
    private String username;
    private String password;
    private String email;
    private String nickName;
    private Integer roleId;
    private String roleName;
    private String userId;
}
