package com.mall.admin.enerty.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/4/1
 * Time:11:15
 * @ action  放回令牌的令牌
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    private Integer tokenTimeout ;
    private String accessToken;
    private String username;
}
