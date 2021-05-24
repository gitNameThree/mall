package com.mall.admin.enerty.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谢成伟
 * Date:2021/5/10
 * Time:16:38
 * @ action  分页查询角色列表的
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {
    private Integer current;
    private Integer pageSize;
    private Integer id;
    private String name;
    private String description;
    private String adminCount;
    private String createTime;
    private Integer status;
}
