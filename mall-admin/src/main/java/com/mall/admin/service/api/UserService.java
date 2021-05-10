package com.mall.admin.service.api;

import com.mall.common.entity.UserInfo;

/**
 * @author 谢成伟
 * Date:2021/4/13
 * Time:10:51
 * @ action  获取用户相关服务
 */

public interface UserService {
    /**
     * 获取当前登录用户的信息
     * @return
     */
    UserInfo getUserInfo();
}
