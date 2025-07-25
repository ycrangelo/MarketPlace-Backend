package com.donedeal.controller;

import com.donedeal.schema.UserSchema;
import com.donedeal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//instance of the service of the user
@Autowired
private UserService userService;

@PostMapping("/user/signup")
public UserSchema postUser(@RequestBody UserSchema userSchema) {
    return userService.postUser(userSchema);
}

@PostMapping("/user/signin")
public Boolean loginUser(@RequestBody UserCreds userCreds) {
    System.out.println(userCreds.getUsername());
    System.out.println(userCreds.getPassword());
    return userService.loginUser(userCreds.getUsername(), userCreds.getPassword());
}


@Data
@NoArgsConstructor
@AllArgsConstructor
static class UserCreds{
    private String username;
    private String password;
}
}
