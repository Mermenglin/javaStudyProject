package com.mmling.mockitodemo.controller;

import com.mmling.mockitodemo.entity.User;
import com.mmling.mockitodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Meimengling
 * @date 2020-11-27 15:10
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("user/{userId}")
    public User say(@PathVariable("userId") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("user/edit")
    public User edit(@RequestBody User user) {
        return userService.edit(user);
    }
}
