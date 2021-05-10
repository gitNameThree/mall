package com.mall.admin.enerty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/4/21
 * Time:10:34
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParams {
    private String username;
    private String password;
}
