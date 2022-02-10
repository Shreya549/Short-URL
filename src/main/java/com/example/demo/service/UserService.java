package com.example.demo.service;

import com.example.demo.model.Url;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(long id);
    User getUserByEmail(String email);
    List<Url> getAllUrl(long id);
    User updateUserById(User user, long id);
    void deleteUser(long id);
}
