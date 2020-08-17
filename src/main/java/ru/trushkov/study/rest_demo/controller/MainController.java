package ru.trushkov.study.rest_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.trushkov.study.rest_demo.model.Role;
import ru.trushkov.study.rest_demo.model.User;
import ru.trushkov.study.rest_demo.repository.RoleRepository;
import ru.trushkov.study.rest_demo.service.RoleServiceImpl;
import ru.trushkov.study.rest_demo.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user_data")
    public ResponseEntity<User> getUserInfo() {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/users/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {

        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> removeUser(@PathVariable (name = "id") long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
