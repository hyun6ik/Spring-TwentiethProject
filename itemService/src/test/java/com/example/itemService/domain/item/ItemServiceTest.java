package com.example.itemService.domain.item;

import com.example.itemService.domain.Item;
import com.example.itemService.domain.ItemRepository;
import com.example.itemService.domain.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void save() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemService.save(item);
        Item findItem = itemService.findItem(item.getId());
        //then
        assertThat(findItem).isEqualTo(savedItem);

    }

    @Test
    public void findAll() throws Exception {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemService.save(item1);
        itemService.save(item2);
        //when
        List<Item> result = itemService.findAllItems();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    public void updateItem() throws Exception {
        //given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemService.update(savedItem.getId(), updateParam);
        Item findItem = itemRepository.findById(savedItem.getId()).orElse(null);
        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}
