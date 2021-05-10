package com.mall.admin.enerty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 谢成伟
 * Date:2021/5/6
 * Time:22:11
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsFlashPage implements Serializable {
    private Integer current;
    private Integer size;
    private String title;
}
