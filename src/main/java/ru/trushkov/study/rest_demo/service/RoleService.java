package ru.trushkov.study.rest_demo.service;

import ru.trushkov.study.rest_demo.model.Role;

public interface RoleService {

    boolean hasRole(String name);

    void addRole(Role name);

    Role getRole(String name);

    void save(Role role);
}
