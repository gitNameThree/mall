package com.mall.admin.service.impl;

import com.mall.admin.enerty.dto.UserInfo;
import com.mall.admin.service.api.UserService;
import com.mall.common.constant.AuthConstant;
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
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        UserInfo userInfo = new UserInfo();
        return userInfo;
    }
}
