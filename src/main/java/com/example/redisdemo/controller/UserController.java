package com.example.redisdemo.controller;

import com.example.redisdemo.bean.User;
import com.example.redisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/findUserInfo/{id}")
    public User findUserInfo(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/api/createUser")
    public void createUser(User user) {
        userService.saveUser(user);

    }
    @RequestMapping(value = "/api/modifyUser")
    public void modifyUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/api/delUser/{id}")
    public void delUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}
