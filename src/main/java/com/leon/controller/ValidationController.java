package com.leon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController
{
    @RequestMapping("/")
    String heartbeat()
    {
        return "Hello World!";
    }
}
