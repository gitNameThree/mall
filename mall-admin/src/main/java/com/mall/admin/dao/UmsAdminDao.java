package com.mall.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.admin.enerty.db.UmsAdmin;
import com.mall.admin.enerty.dto.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:11:11
 * @ action  获取文件的dao
 */
@Repository
public interface UmsAdminDao extends BaseMapper<UmsAdmin> {
    /**
     * 通过用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfo getUmsAdminByUserName(@Param("userName") String userName);
}
