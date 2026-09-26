package com.example.taskmanager.repository;

import com.example.taskmanager.domain.Role;
import com.example.taskmanager.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
