package com.mall.admin.enerty.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.admin.enerty.vi.Menuvi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:16:06
 * @ action  表ums_menu的映射
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_menu")
public class Menu {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "parent_id")
    private Integer parentId;
    @TableField(value = "title")
    private String title;
    @TableField(value = "level")
    private Integer level;
    @TableField(value = "sort")
    private Integer sort;
    @TableField(value = "name")
    private String name;
    @TableField(value = "icon")
    private String icon;
    @TableField(value = "hidden")
    private Integer hidden;
    @TableField(value = "url")
    private String url;

    private List<Menu> children=new LinkedList<>();

}
