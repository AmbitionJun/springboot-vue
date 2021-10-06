package com.ambitionjun.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody   /*将java对象转为json格式的数据 返回结果直接写入response body */
//@Controller

@RestController  /*是@ResponseBody @Controller 合体*/
public class HelloController {

    @RequestMapping("/hello")
    public String hello1(){
       return "Hello, Spring Boot";
    }
}
