package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User getUserById(int id);

    void save(User user);

    void delete(User user);

    User findByUsername(String username);

    User passwordCoder(User user);

    User findByEmail(String email);

    void editUser(User user);
}