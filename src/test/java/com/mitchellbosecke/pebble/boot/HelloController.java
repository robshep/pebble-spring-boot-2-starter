package com.mitchellbosecke.pebble.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello.action")
    public String hello() {
        return "hello";
    }

}