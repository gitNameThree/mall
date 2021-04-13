package com.mall.admin.service.api;

import com.mall.admin.enerty.db.Menu;
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
     *返回一级菜单
     * @return list
     */
    List<Menu> findFirstMenuList();


    /**
     * 认证服务器申请令牌，存储令牌
     * @param url 认证服务器地址
     * @param multiValueMapHttpEntity 请求对象
     * @return  AuthToken
     */
    AuthToken applyForToken(String url, HttpEntity<MultiValueMap<String,Object >> multiValueMapHttpEntity);

}
