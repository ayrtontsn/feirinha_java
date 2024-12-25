package com.feirinha.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.repositories.ItemsRepository;

import jakarta.validation.Valid;

import com.feirinha.api.dtos.ItemsDTO;
import com.feirinha.api.models.ItemsModel;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/items")
public class ItemsController {

    final ItemsRepository itemsRepository;

    ItemsController(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    
    @GetMapping
    public List<ItemsModel> getItems() {
        return itemsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ItemsModel> getItemById(@PathVariable("id") Long id){
        Optional<ItemsModel> item = itemsRepository.findById(id);
        if (!item.isPresent()){
            return Optional.empty();
        }

        return Optional.of(item.get());
    }
    
    @PostMapping
    public void postItem(@RequestBody @Valid ItemsDTO body) {
        
        ItemsModel item = new ItemsModel(body);
        itemsRepository.save(item);
    }
    
}