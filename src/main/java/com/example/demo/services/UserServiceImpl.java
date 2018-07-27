package com.example.demo.services;

import com.example.demo.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public User updateUser(User bank) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails)auth.getPrincipal();
        return auth.getName();
    }
}
