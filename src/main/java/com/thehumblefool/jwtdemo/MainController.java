package com.thehumblefool.jwtdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping(path = "/secured/hello")
    public String greet() {
        return "Hello world!";
    }
}