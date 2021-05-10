package com.mall.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.admin.enerty.db.Menu;
import com.mall.admin.enerty.db.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:12
 * @ action  请表明此文件的用途
 */
@Repository
public interface MenuDao extends BaseMapper<Menu> {
    /**
     * 更具角色ID获取菜单列表
     * @param roleId 角色Id
     * @return
     */
    List<Menu> findMenuList(@Param("roleId")Integer roleId);

    /**
     * 返回所有的菜单
     * @param level 菜单的级别
     * @return list<Menu>
     */
    List<Menu> findMenuListByLevel(@Param("level") int level);


}
