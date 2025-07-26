package com.donedeal.controller;

import com.donedeal.schema.UserSchema;
import com.donedeal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    System.out.println(userSchema);
    return userService.postUser(userSchema);
}

@PostMapping("/user/signin")
public Boolean loginUser(@RequestBody UserCreds userCreds) {
    System.out.println("hola signin");
    System.out.println(userCreds.getUsername());
    System.out.println(userCreds.getPassword());
    return userService.loginUser(userCreds.getUsername(), userCreds.getPassword());
}


// this is for setter and getter
@Data
//this is for constructor
@NoArgsConstructor
@AllArgsConstructor
// use in  DTA ATA? OR JDC? dont new pero parang pang sanitize or centrilize ito eh
static class UserCreds{
    private String username;
    private String password;
}
}
