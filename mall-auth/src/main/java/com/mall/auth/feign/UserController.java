package com.mall.auth.feign;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢成伟
 * Date:2021/4/26
 * Time:14:54
 * @ action  请表明此文件的用途
 */
@RestController
@Log4j2
public class UserController {
    @RequestMapping("/oauth/user")
    public String getUser(){
        log.info("wo---------------------------------------------------");
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "admin";
    }
}
