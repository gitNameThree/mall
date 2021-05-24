package com.mall.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.enerty.db.Role;
import com.mall.admin.enerty.dto.LoginParams;
import com.mall.admin.enerty.dto.RoleDto;
import com.mall.admin.enerty.vi.AuthToken;
import com.mall.admin.fegin.FeginService;
import com.mall.admin.service.api.PermitService;
import com.mall.admin.service.api.UserService;
import com.mall.common.advice.response.ControllerHandle;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/18
 * Time:9:54
 * @action 用作登录的作用
 */
@Log4j2
@RestController
@ControllerHandle
@RequestMapping("/permit")
public class PermitController {

    @Autowired
    PermitService permitService;

    @Autowired
    UserService userService;

    @Autowired
    FeginService feginService;

    @GetMapping("/findMenuList")
    private List findMenuList() {

        return permitService.findMenuList();
    }

    @PostMapping("/findRoleList")
    private Page<Role> findRoleList(@RequestBody RoleDto roleDto) {
        return permitService.findRoleList(roleDto);
    }

    @GetMapping("/getUser")
    private String getUser() {
        return feginService.getUserInfo();
    }

    @PostMapping("/login")
    private AuthToken login(@RequestBody LoginParams loginParams) {
        return permitService.applyForToken(loginParams);

    }

    @GetMapping("/loginOut")
    private boolean loginOut() {
        return permitService.loginOut();

    }

    @PostMapping("/editRole")
    private boolean editRole(@RequestBody RoleDto roleDto) {
        return permitService.editRole(roleDto);

    }

    @GetMapping("/getRole")
    private Role getRole(@RequestParam Integer roleId) {
        return permitService.getRoleById(roleId);

    }

    @PostMapping("/changeStatus")
    private boolean changeStatus(@RequestBody RoleDto roleDto) {
        return permitService.changeStatus(roleDto.getId(),roleDto.getStatus());

    }


    @GetMapping("/deleteRole")
    private boolean deleteRole(@RequestParam Integer roleId) {
        return permitService.deleteRole(roleId);
    }

}