package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);
}
