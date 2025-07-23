package com.donedeal.repository;

import com.donedeal.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;


//used to access the schema or the table or the Entity
// WE CANT DIRECTLY USE THE ENTITY OR TABLE OR SCHEMA
public interface UserRepository extends JpaRepository<UserSchema,Integer> {

}
