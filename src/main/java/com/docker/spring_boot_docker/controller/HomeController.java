package com.docker.spring_boot_docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello Docker World!";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello Docker Test!";
    }

    @GetMapping("/test/{id}")
    public String testId(@PathVariable String id) {
        return "Hello Docker Test ID: " + id;
    }
}
