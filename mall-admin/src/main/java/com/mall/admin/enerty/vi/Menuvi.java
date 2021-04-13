package com.mall.admin.enerty.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 谢成伟
 * Date:2021/3/19
 * Time:17:22
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menuvi {
    private String id;
    private Integer parentId;
    private String title;
    private Integer level;
    private Integer sort;
    private String name;
    private String icon;
    private Integer hidden;
    private List<Menuvi> children;
}
