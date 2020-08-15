package ru.trushkov.study.rest_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trushkov.study.rest_demo.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
