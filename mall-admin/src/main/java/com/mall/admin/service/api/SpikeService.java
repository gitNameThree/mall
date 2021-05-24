package com.mall.admin.service.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.enerty.db.SmsFlashPromotion;
import com.mall.admin.enerty.dto.SpikeProductDto;

/**
 * @author 谢成伟
 * Date:2021/5/6
 * Time:22:14
 * @ action  请表明此文件的用途
 */
public interface SpikeService {

    /**
     * 分页查询秒杀活动的列表
     * @param page 分页参数
     * @param smsFlashPromotion 秒杀活动表对应的实体类
     * @return
     */
    Page<SmsFlashPromotion> findSmsFlashPromotionList(Page page, SmsFlashPromotion smsFlashPromotion);

    /**
     * 添加和编辑活动列表
     * @param smsFlashPromotion 秒杀活动信息
     * @return true：编辑成功 false:编辑失败
     */
    boolean editSpike(SmsFlashPromotion smsFlashPromotion);

    /**
     * 删除活动
     * @param spikeId 活动Id
     * @return
     */
    boolean deleteSpike(Integer spikeId);

    /**
     * 编辑秒杀
     * @param spikeProductDto
     * @return
     */
    boolean editFlashPromotion(SpikeProductDto spikeProductDto);

    /**
     * 秒杀商品预热
     * @param productId 商品的Id
     * @return
     */
    boolean warmUpProduct(Integer productId);
}
