package ru.trushkov.study.rest_demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trushkov.study.rest_demo.model.Role;
import ru.trushkov.study.rest_demo.repository.UserRepository;
import ru.trushkov.study.rest_demo.model.User;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl() {
    }
    @Transactional
    @Override
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role: user.getRoles()) {
            if (roleService.hasRole(role.getName())) {
                roles.add(roleService.getRole(role.getName()));
            } else {
                Role newRole = new Role();
                newRole.setName(role.getName());
                roleService.save(newRole);
                roles.add(newRole);
            }
        }
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return userRepository.getOne(id);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Transactional
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
