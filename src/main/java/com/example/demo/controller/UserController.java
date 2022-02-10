package com.example.demo.controller;

import com.example.demo.model.Url;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    // save user
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/id")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id") long id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email") String email) {
        return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/get/all/{id}")
    public ResponseEntity<List<Url>> getAllUrl(@PathVariable("id") long id){
        return new ResponseEntity<List<Url>>(userService.getAllUrl(id), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") long id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUserById(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }
}
