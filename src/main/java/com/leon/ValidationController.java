package com.leon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
