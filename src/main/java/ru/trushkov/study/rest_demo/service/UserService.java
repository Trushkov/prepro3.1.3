package ru.trushkov.study.rest_demo.service;

import ru.trushkov.study.rest_demo.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUser(Long id);

    void remove(Long id);

    void updateUser(User user);

    List<User> getUsers();
}
