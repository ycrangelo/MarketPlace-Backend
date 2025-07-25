package com.donedeal.service;


import com.donedeal.repository.UserRepository;
import com.donedeal.schema.UserSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // using UserSchema as return of the route or function
    // in the parameter we use the object so that the fields are the variables of an Schema
    public UserSchema postUser( UserSchema userSchema) {

        // using userRepo( or the UserReposity)cuz we cannot user directly the schema
        // POST GET UPDATE PUT DELETE
        return userRepo.save(userSchema);
    }

    public Boolean loginUser(String username, String password) {
//        System.out.println("this is service username "+username);
        System.out.println("this is service password "+password);
        //this is a CLASS YUNG OPTIONAL
        //Optional not optional
     Optional<UserSchema> userSchema = userRepo.findByUsernameAndPassword(username,password);
     System.out.println("this is the user Service login userSchema "+ userSchema);

     if(userSchema.isPresent()){
         UserSchema userLogin = userSchema.get();
         return true;
     };
     return false;
    };
}
