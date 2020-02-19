package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {
    @Value("${person.name}")
    private String name;

    @RequestMapping("/test2")
    public String test2(){
        return name;
    }
}
