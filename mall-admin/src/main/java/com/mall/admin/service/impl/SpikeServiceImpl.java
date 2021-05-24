package com.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.dao.SmsFlashPromotionDao;
import com.mall.admin.dao.impl.SmsFlashServiceImplDao;
import com.mall.admin.enerty.db.SmsFlashPromotion;

import com.mall.admin.enerty.dto.SpikeProductDto;
import com.mall.admin.service.api.SpikeService;
import com.mall.common.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 谢成伟
 * Date:2021/5/6
 * Time:22:15
 * @ action  请表明此文件的用途
 */
@Service
public class SpikeServiceImpl implements SpikeService {

    @Autowired
    SmsFlashServiceImplDao smsFlashServiceImplDao;

    @Autowired
    RedisService redisService;

    @Override
    public Page<SmsFlashPromotion> findSmsFlashPromotionList(Page page, SmsFlashPromotion smsFlashPromotion) {
        QueryWrapper<SmsFlashPromotion> wrapper = new QueryWrapper<>();
        return smsFlashServiceImplDao.page(page,wrapper);
    }

    @Override
    public boolean editSpike(SmsFlashPromotion smsFlashPromotion) {
        return smsFlashServiceImplDao.saveOrUpdate(smsFlashPromotion);
    }

    @Override
    public boolean deleteSpike(Integer spikeId) {
        return smsFlashServiceImplDao.removeById(spikeId);
    }

    @Override
    public boolean editFlashPromotion(SpikeProductDto spikeProductDto) {
        // 先删除redis缓存
        // 保存到数据库
        // 新建缓存到
        return false;
    }

    @Override
    public boolean warmUpProduct(Integer productId) {
        //查询商品库存信息
        //缓存数据，设置失效时间
//        redisService.set();
        return false;
    }
}
