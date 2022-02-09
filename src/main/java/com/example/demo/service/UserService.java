package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(long id);
    User getUserByEmail(String email);
    User updateUserById(User user, long id);
    User updateUserByEmail(User user, String email);
    void deleteUser(long id);
}
