package ru.trushkov.study.rest_demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trushkov.study.rest_demo.repository.UserRepository;
import ru.trushkov.study.rest_demo.model.User;


import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
