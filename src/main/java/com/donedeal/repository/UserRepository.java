package com.donedeal.repository;

import com.donedeal.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//used to access the schema or the table or the Entity
// WE CANT DIRECTLY USE THE ENTITY OR TABLE OR SCHEMA
public interface UserRepository extends JpaRepository<UserSchema,Integer> {

    Optional<UserSchema> findByUsername(String username);
    Optional<UserSchema> findByPassword(String password);

    Optional<UserSchema> findByUsernameAndPassword(String username, String password);
}
