package com.mall.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.admin.enerty.db.SmsFlashPromotion;
import com.mall.admin.enerty.dto.SmsFlashPage;
import com.mall.admin.enerty.dto.SpikeProductDto;
import com.mall.admin.service.api.SpikeService;
import com.mall.common.advice.response.ControllerHandle;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 谢成伟
 * Date:2021/5/6
 * Time:22:05
 * @ action  请表明此文件的用途
 */
@RestController
@ControllerHandle
@RequestMapping("spike")
public class SpikeController {

    @Resource
    RedissonClient redissonClient;

    @Autowired
    SpikeService spikeService;

    @PostMapping("findSmsFlashPromotionList")
    Page<SmsFlashPromotion> findSmsFlashPromotionList(@RequestBody SmsFlashPage smsFlashPage) {
        Page page = new Page(smsFlashPage.getCurrent(), smsFlashPage.getSize());
        SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();
        smsFlashPromotion.setTitle(smsFlashPage.getTitle());
        return spikeService.findSmsFlashPromotionList(page, smsFlashPromotion);
    }

    @PostMapping("editSpike")
    boolean editSpike(@RequestBody SmsFlashPromotion smsFlashPromotion) {
        return spikeService.editSpike(smsFlashPromotion);
    }

    @GetMapping("deleteSpike")
    boolean deleteSpike(@PathVariable Integer spikeId){
        return spikeService.deleteSpike(spikeId);
    }

    @PostMapping("editFlashPromotion")
    boolean editFlashPromotion(@RequestBody SpikeProductDto spikeProductDto){
        return spikeService.editFlashPromotion(spikeProductDto);
    }

}
