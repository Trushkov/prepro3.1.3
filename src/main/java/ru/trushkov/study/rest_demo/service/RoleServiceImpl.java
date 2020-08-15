package ru.trushkov.study.rest_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trushkov.study.rest_demo.model.Role;
import ru.trushkov.study.rest_demo.repository.RoleRepository;


import static java.util.Objects.isNull;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

    @Override
    public boolean hasRole(String name){
        return !isNull(roleRepository.findByName(name));
    }

    @Override
    public void addRole(Role name) {
        roleRepository.save(name);
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(Role role){
        roleRepository.save(role);
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
