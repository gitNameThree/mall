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
 * Date:2021/5/6
 * Time:21:47
 * @ action  秒杀活动列表对应的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sms_flash_promotion")
public class SmsFlashPromotion {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "title")
    private String title;
    @TableField(value = "start_date")
    private String startDate;
    @TableField(value = "end_date")
    private String endDate;
    @TableField(value = "status")
    private Integer status;
}
