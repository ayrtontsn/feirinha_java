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

    public Optional<ItemsModel> save(ItemsDTO dto){
        
        if(itemsRepository.existsByName(dto.getName())){
            return Optional.empty();
        }
        ItemsModel item = new ItemsModel(dto);

        return Optional.of(itemsRepository.save(item));
    }

    public Optional<ItemsModel>  update(Long id, ItemsDTO dto){
        if(itemsRepository.existsByName(dto.getName())){
            return Optional.empty();
        }
        ItemsModel item = new ItemsModel(dto);
        item.setId(id);

        return Optional.of(itemsRepository.save(item));
    }

    public void deleteById(Long id){
        itemsRepository.deleteById(id);
    }
}
