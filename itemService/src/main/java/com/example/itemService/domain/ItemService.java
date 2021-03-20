package com.example.itemService.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Item findItem(Long itemId){
        return itemRepository.findById(itemId).orElse(null);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    @Transactional
    public void update(Long itemId, Item updateParam){
        Item findItem = itemRepository.findById(itemId).orElse(null);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
}
