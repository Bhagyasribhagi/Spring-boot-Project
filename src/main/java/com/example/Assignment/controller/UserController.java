package com.example.Assignment.controller;

import com.example.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Assignment.model.User;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    public UserService userService;
    //get all users data
    @GetMapping("/get_users")
    public ArrayList<User> getUser(){
        return userService.getUser();
    }
    //add user data
    @PostMapping("/add_user")
    public User addUser(@RequestBody  User user){
        return userService.addUser(user);
    }
    //get user data by userId
    @GetMapping("/get_user/{userId}")
    public User getUserById(@PathVariable ("userId") UUID userId){
       return userService.getUserById(userId);
    }
    //update user data
    @PostMapping("/update_user")
    public ArrayList<User> updateUser(@RequestBody  ArrayList<User> usersList){
        return userService.updateUser(usersList);
    }
    //get user data by userId and mobile number
    @PostMapping("/get_user")
    public ArrayList<User> getUserData(@RequestBody User userDetails){
        return userService.getUserData(userDetails);

    }
    //delete user data by userId and mobile number
    @PostMapping("/delete_user")
    public String deleteUser(@RequestBody User userData){
         return userService.deleteUser(userData);
    }


}
