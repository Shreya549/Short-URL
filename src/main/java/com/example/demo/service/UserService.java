package com.example.demo.service;

import com.example.demo.model.Url;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(String id);
    User getUserByEmail(String email);
    List<Url> getAllUrl(String id);
    User updateUserById(User user, String id);
    void deleteUser(String id);
}
