package ru.trushkov.study.rest_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trushkov.study.rest_demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);
}
