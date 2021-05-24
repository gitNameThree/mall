package com.mall.admin.service.api;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 谢成伟
 * Date:2021/5/19
 * Time:8:51
 * @ action  请表明此文件的用途
 */
@Log4j2
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpikeServiceTest {


    @Test
    void warmUpProduct() {


    }


}