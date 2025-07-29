package com.donedeal.service;


import com.donedeal.LocalSession;
import com.donedeal.repository.UserRepository;
import com.donedeal.schema.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LocalSession localSession;


    // using UserSchema as return of the route or function
    // in the parameter we use the object so that the fields are the variables of an Schema
    public UserSchema postUser( UserSchema userSchema) {
        //Ecrypting the password
        String encodedPassword = passwordEncoder.encode(userSchema.getPassword());
        userSchema.setPassword(encodedPassword);
        // using userRepo( or the UserReposity)cuz we cannot user directly the schema
        // POST GET UPDATE PUT DELETE
        return userRepo.save(userSchema);
    }

    public Boolean loginUser(String username, String password) {
        boolean result = false;
//        System.out.println("this is service username "+username);
//        System.out.println("this is service password "+password);
        //this is a CLASS YUNG OPTIONAL
        //Optional not optional
//     Optional<UserSchema> userSchema = userRepo.findByUsernameAndPassword(username,password);
     Optional<UserSchema> userSchema = userRepo.findByUsername(username);

     if(userSchema.isPresent()){
         UserSchema userLogin = userSchema.get();
//         System.out.println("this is the user Service login userSchema "+ userLogin);
         String userEncryptedPassword = userLogin.getPassword();
         if(passwordEncoder.matches(password,userEncryptedPassword)){
//             System.out.println("username in the userschema "+ userSchema.get().getUsername());
             localSession.setUsername(userSchema.get().getUsername());
//             System.out.println("username in the localSession "+ localSession.getUsername());
             result = true;
         }
     }
        return result;
    };

}
