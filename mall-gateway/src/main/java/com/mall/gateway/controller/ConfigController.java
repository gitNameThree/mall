package com.mall.gateway.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 谢成伟
 * Date:2021/3/16
 * Time:15:18
 */
@RestController
@RequestMapping("/config")
public class ConfigController {


        @GetMapping("/getConfigInfo")
    private String getConfigInfo(){
        return "ssss";
    }
}
