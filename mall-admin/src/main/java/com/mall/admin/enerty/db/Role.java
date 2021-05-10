package com.mall.admin.enerty.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/4/25
 * Time:15:13
 * @ action  角色表映射类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_role")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "description")
    private String description;
    @TableField(value = "admin_count")
    private String adminCount;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "status")
    private Integer status;

}
