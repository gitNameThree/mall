package com.mall.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mall.admin.service.api.UserService;
import com.mall.common.constant.AuthConstant;
import com.mall.common.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 谢成伟
 * Date:2021/4/13
 * Time:10:58
 * @ action  用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpServletRequest request;


    @Override
    public UserInfo getUserInfo() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        String userId = request.getHeader(AuthConstant.USER_ID_TOKEN_HEADER);
        UserInfo userInfo = JSONObject.parseObject(userStr,UserInfo.class);
        userInfo.setUserId(userId);
        return userInfo;
    }
}
