package com.lei.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/quick")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick!";
    }


}
