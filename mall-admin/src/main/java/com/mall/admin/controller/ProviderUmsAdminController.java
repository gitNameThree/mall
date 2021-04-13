package com.mall.admin.controller;

import com.mall.admin.dao.UmsAdminDao;
import com.mall.admin.enerty.db.UmsAdmin;
import com.mall.admin.enerty.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:11:10
 * @ action  请表明此文件的用途
 */
@RestController
public class ProviderUmsAdminController  {

    @Autowired
    UmsAdminDao umsAdminDao;

    @PostMapping("/getUmsAdminByUserName")
    public UserInfo getUmsAdminByUserName(@RequestParam("userName")String userName) {
        return umsAdminDao.getUmsAdminByUserName(userName);
    }
}
