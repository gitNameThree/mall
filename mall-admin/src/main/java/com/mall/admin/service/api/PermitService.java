package com.mall.admin.service.api;

import com.mall.admin.enerty.db.Menu;
import com.mall.admin.enerty.db.Role;
import com.mall.admin.enerty.dto.LoginParams;
import com.mall.admin.enerty.vi.AuthToken;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:16
 * @ action  权限相关的service层
 */

public interface PermitService {
    /**
     * 返回菜单树
     *
     * @return
     */
    List<Menu> findMenuList();

    /**
     * 返回角色列表
     *
     * @return
     */
    List<Role> findRoleList();



    /**
     * 登录请求
     * @param loginParams
     * @return
     */
    AuthToken applyForToken(LoginParams loginParams);

    /**
     * 退出登录接口
     * @return
     */
    boolean loginOut();

}
