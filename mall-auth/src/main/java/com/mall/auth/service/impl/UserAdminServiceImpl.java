package com.mall.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.mall.auth.enerty.UserDetailImpl;
import com.mall.auth.threeapi.ProviderUmsAdminService;
import com.mall.common.advice.response.UnityResult;
import com.mall.common.enerty.ResultCodeEnum;
import com.mall.common.enerty.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author 谢成伟
 * Date:2021/3/29
 * Time:14:20
 * @ action  自定义登录逻辑
 */
@Service
@Log4j2
public class UserAdminServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ProviderUmsAdminService providerUmsAdminService;

    /**
     * 登录认证的用户名和密码
     *
     * @param userName 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // 查找用户信息
        UnityResult unityResult = providerUmsAdminService.getUmsAdminByUserName(userName);
        if (!Objects.equals(unityResult.getCode(), ResultCodeEnum.SUCCESS_CODE.getCode())) {
            throw new UsernameNotFoundException("暂时无法提供认证服务");
        }
        UserInfo userInfo =
                BeanUtil.mapToBean((Map<?, ?>) unityResult.getData(),UserInfo.class,false,new CopyOptions());
        if (Strings.isNullOrEmpty(userInfo.getPassword())) {
            throw new UsernameNotFoundException("暂时无法提供认证服务");
        }
        // 返回认证后的用户信息
        UserDetailImpl userDetail = new UserDetailImpl();
        BeanUtil.copyProperties(userInfo, userDetail, true);
        userDetail.setPassword( passwordEncoder.encode(userInfo.getPassword()));
        return userDetail;

    }
}