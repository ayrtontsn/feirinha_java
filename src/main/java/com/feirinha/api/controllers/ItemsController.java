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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(item.get());
    }
    
    @PostMapping
    public ResponseEntity<Object> postItem(@RequestBody @Valid ItemsDTO body) {
        
        Optional<ItemsModel> item = itemsService.save(body);

        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Repeated name");
        } 
        return ResponseEntity.status(HttpStatus.CREATED).body(item.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable Long id, @RequestBody @Valid ItemsDTO body) {
        if(!itemsService.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        Optional<ItemsModel> item = itemsService.update(id, body);
        if(!item.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Repeated name");
        }
        return ResponseEntity.status(HttpStatus.OK).body(item.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id){
        if(!itemsService.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
        itemsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}