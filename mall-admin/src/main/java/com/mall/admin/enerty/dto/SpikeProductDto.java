package com.mall.admin.enerty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 谢成伟
 * Date:2021/5/7
 * Time:14:25
 * @ action  请表明此文件的用途
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpikeProductDto implements Serializable {
    private Long productId;
    private Long flashPromotionSessionId;
    private String name;
    private String productSn;
    private String price;
    private String  stock;
    private double flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}
