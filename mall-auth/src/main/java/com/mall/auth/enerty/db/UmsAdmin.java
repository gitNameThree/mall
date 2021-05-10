package com.mall.auth.enerty.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/3/31
 * Time:11:01
 * @ action  后台用户表映射类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_admin")
public class UmsAdmin {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "icon")
    private String icon;
    @TableField(value = "email")
    private String email;
    @TableField(value = "nick_name")
    private String nickName;
    @TableField(value = "note")
    private String note;
    @TableField(value = "status")
    private Integer status;
}

