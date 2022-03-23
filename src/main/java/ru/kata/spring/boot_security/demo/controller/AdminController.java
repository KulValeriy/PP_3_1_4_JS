package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    private UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @Autowired
    public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/admin")
    public String users(Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        return "users";
    }

    @GetMapping(value = "admin/user")
    public String getUser(@RequestParam(value = "id", required = false) int id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        return "user";
    }

    @GetMapping(value = "admin/edit")
    public String edit(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.getById(id));
        return "editPage";
    }

    @PostMapping("admin/edit")
    public String pageEdit(@RequestParam("role") List<Integer> roles,
                           @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editPage";
        } else {
            user.setRoles(roleService.findByIdRoles(roles));
            userServiceImpl.save(user);
            return "redirect:/admin";
        }
    }

    @GetMapping("admin/add")
    public String add(User user) {
        return "add";
    }

    @PostMapping("admin/add")
    public String pageAdd(@RequestParam("role") List<Integer>  roles,
                          @ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }

        user.setRoles(roleService.findByIdRoles(roles));
        userServiceImpl.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userServiceImpl.deleteById(id);
        return "redirect:/admin";
    }
}
