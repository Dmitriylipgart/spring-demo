package com.example.demo.repositories;


import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> findAll();
}
