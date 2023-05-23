package com.proit.todo.service;

import com.proit.todo.dto.ItemDto;
import com.proit.todo.entity.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    Item update(ItemDto itemReq, Item dbItem);
    List<Item> getItems();
    Optional<Item> findById(Integer id);
    void delete(Item item);
}
