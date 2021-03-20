package com.example.itemService.web.basic;

import com.example.itemService.domain.Item;
import com.example.itemService.domain.ItemRepository;
import com.example.itemService.domain.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스터용 아이템 추가
     */
    @PostConstruct
    public void init(){
        itemService.save(new Item("itemA", 10000,10));
        itemService.save(new Item("itemB", 20000,20));
    }
}
