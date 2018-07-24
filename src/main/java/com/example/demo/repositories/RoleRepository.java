package com.example.demo.repositories;


import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    public Role getRoleById(Long id);
}
