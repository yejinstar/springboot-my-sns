package com.likelion.finalprojectsns.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("api/v1/hello")
    public String hello(){
        return "darkchocolate";
    }
}
