package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@EnableWebMvc
public class AdminController {

    @GetMapping("/admin")
    public String index() {
        return "adminJS";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }


}
