package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author GodXuan
 * @date 2019/11/18 0018 - 下午 9:10
 */
@ApiIgnore
@RestController
public class HelloController {

    final static  Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public Object hello(){

        LOGGER.warn("warn hello");
        LOGGER.debug("debug hello");
        LOGGER.error("error hello");
        LOGGER.info("info hello");


        return "Hello world!";
    }
}
