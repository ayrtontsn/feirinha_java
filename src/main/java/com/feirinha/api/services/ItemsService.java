package com.feirinha.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feirinha.api.dtos.ItemsDTO;
import com.feirinha.api.models.ItemsModel;
import com.feirinha.api.repositories.ItemsRepository;

@Service
public class ItemsService {
    final ItemsRepository itemsRepository;

    ItemsService(ItemsRepository itemsRepository){
        this.itemsRepository = itemsRepository;
    }

    public List<ItemsModel> findAll(){
        return itemsRepository.findAll();
    }
    
    public Optional <ItemsModel> findById(Long id){
        return itemsRepository.findById(id);
    }

    public ItemsModel save(ItemsDTO dto){
        ItemsModel item = new ItemsModel(dto);

        return itemsRepository.save(item);
    }
}
