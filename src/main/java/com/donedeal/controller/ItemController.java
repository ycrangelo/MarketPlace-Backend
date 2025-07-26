package com.donedeal.controller;

import com.donedeal.schema.ItemSchema;
import com.donedeal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/post/item")
    public ItemSchema postItem(@RequestBody  ItemSchema item){
        return itemService.postItem(item);
    }

    @GetMapping("/get/allItems")
    public List<ItemSchema> getAllItem(){
        return itemService.getAllItems();
    }
}
