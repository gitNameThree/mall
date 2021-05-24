package com.mall.admin.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.enerty.db.Menu;
import com.mall.admin.enerty.db.Role;
import com.mall.admin.enerty.dto.LoginParams;
import com.mall.admin.enerty.dto.RoleDto;
import com.mall.admin.enerty.vi.AuthToken;

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
     * 查询角色列表
     *
     * @param roleDto 查询条件
     * @return
     */
    Page<Role> findRoleList(RoleDto roleDto);


    /**
     * 登录请求
     *
     * @param loginParams
     * @return
     */
    AuthToken applyForToken(LoginParams loginParams);

    /**
     * 退出登录接口
     *
     * @return
     */
    boolean loginOut();

    /**
     * 通过角色ID 查询角色
     * @param roleId 角色ID
     * @return
     */
    Role getRoleById(Integer roleId);

    /**
     * 添加角色
     * @param roleDto 角色信息
     * @return
     */
    boolean editRole(RoleDto roleDto);

    /**
     * 添加角色
     * @param roleId 角色ID
     * @return
     */
    boolean deleteRole(Integer roleId);

    /**
     * 修改角色是否启用的状态
     * @param roleId  角色ID
     * @param status 角色状态
     * @return true 操作成功，false 操作失败
     */
    boolean changeStatus(Integer roleId,Integer status);


}
