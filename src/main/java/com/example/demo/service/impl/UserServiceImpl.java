package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Url;
import com.example.demo.model.User;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UrlRepository urlRepository;

    public UserServiceImpl(UserRepository userRepository, UrlRepository urlRepository) {
        super();
        this.userRepository = userRepository;
        this.urlRepository = urlRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
    }

    @Override
    public User getUserByEmail(String email) {
        User existingUser =  userRepository.findByEmail(email);

        if (existingUser != null){
            return existingUser;
        }
        else throw new ResourceNotFoundException("User", "Email", email);
    }

    @Override
    public List<Url> getAllUrl(long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return urlRepository.findAllByUser(existingUser);
    }


    @Override
    public User updateUserById(User user, long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        urlRepository.deleteAllByUser(existingUser);
        userRepository.deleteById(id);
    }
}
