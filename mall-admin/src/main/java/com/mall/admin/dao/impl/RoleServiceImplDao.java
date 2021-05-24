package com.mall.admin.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.admin.dao.RoleDao;
import com.mall.admin.dao.SmsFlashPromotionDao;
import com.mall.admin.dao.service.RoleServiceDao;
import com.mall.admin.dao.service.SmsFlashServiceDao;
import com.mall.admin.enerty.db.Role;
import com.mall.admin.enerty.db.SmsFlashPromotion;
import org.springframework.stereotype.Repository;

/**
 * @author 谢成伟
 * Date:2021/5/7
 * Time:8:58
 * @ action  请表明此文件的用途
 */
@Repository
public class RoleServiceImplDao
        extends ServiceImpl<RoleDao, Role> implements RoleServiceDao {

}
