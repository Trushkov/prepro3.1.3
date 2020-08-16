package ru.trushkov.study.rest_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trushkov.study.rest_demo.model.Role;
import ru.trushkov.study.rest_demo.repository.RoleRepository;


import static java.util.Objects.isNull;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

    @Transactional
    @Override
    public boolean hasRole(String name){
        return !isNull(roleRepository.findByName(name));
    }

    @Transactional
    @Override
    public void addRole(Role name) {
        roleRepository.save(name);
    }

    @Transactional
    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    @Override
    public void save(Role role){
        roleRepository.save(role);
    }

    @Transactional
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    @Transactional
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}
