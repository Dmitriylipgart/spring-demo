package com.example.demo.services;


import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    User getByName(String name);
    User updateUser(String user, String role);
    void delete(long id);
    List<User> getAll();
}
