package com.example.demo.services;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public void updateUser(String username, String rolename) {

        User user = users.findByUsername(username);
        Set<Role> set = user.getRoles();
        Role role = roles.getRoleByName(rolename);
        set.add(role);
        user.setRoles(set);
        users.saveAndFlush(user);

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {

        return users.findAll();
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails)auth.getPrincipal();
        return auth.getName();
    }


}
