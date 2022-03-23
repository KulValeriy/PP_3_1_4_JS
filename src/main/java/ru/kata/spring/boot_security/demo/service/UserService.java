package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    User getById(int id);

    void save(User user);

    void deleteById(int id);

    User findByUsername(String username);

    User passwordCoder(User user);
}
