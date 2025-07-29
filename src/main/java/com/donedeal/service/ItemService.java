package com.donedeal.service;


import com.donedeal.repository.ItemRepository;
import com.donedeal.schema.ItemSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public ItemSchema postItem(ItemSchema item){
        return itemRepository.save(item);
    }

    public List<ItemSchema> getAllItems(){
        return itemRepository.findAllByStatus(1);
    }
}
