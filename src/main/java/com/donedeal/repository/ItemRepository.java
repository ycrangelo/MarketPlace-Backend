package com.donedeal.repository;

import com.donedeal.schema.ItemSchema;
import com.donedeal.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemSchema,Integer> {
    Optional<ItemSchema> findById(int id);

    
}
