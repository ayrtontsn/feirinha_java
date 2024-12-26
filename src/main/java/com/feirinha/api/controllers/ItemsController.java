package com.feirinha.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.services.ItemsService;

import jakarta.validation.Valid;

import com.feirinha.api.dtos.ItemsDTO;
import com.feirinha.api.models.ItemsModel;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/items")
public class ItemsController {

    final ItemsService itemsService;

    ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }
    
    @GetMapping
    public ResponseEntity<Object> getItems() {
        return ResponseEntity.status(HttpStatus.OK).body(itemsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable("id") Long id){
        Optional<ItemsModel> item = itemsService.findById(id);
        if (!item.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(item.get());
    }
    
    @PostMapping
    public ResponseEntity<Object> postItem(@RequestBody @Valid ItemsDTO body) {
        
        ItemsModel item = itemsService.save(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    
}