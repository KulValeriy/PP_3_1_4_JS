package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import java.security.Principal;


@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userService) {
        this.userServiceImpl = userService;
    }


    @GetMapping(value = "/user")
    public String getUser(Model model, Principal principal) {
        model.addAttribute("user", userServiceImpl.findByUsername(principal.getName()));
        return "user";
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}