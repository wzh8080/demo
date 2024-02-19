package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhwang
 * @Date 2022/9/28 13:33
 */
@RestController
public class DemoController {
    @RequestMapping("/test1")
    public String test1() {
        System.out.println("测试1");
        return "我是 测试1";
    }
}
