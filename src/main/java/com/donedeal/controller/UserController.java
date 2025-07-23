package com.donedeal.controller;

import com.donedeal.schema.UserSchema;
import com.donedeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//instance of the service of the user
@Autowired
private UserService userService;

@PostMapping("/createUser")
public UserSchema postUser(@RequestBody UserSchema userSchema) {
    return userService.postUser(userSchema);
}
}
