package com.proit.todo.serviceImpl;

import com.proit.todo.dto.ItemDto;
import com.proit.todo.entity.Item;
import com.proit.todo.repository.ItemRepository;
import com.proit.todo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item update(ItemDto itemReq, Item dbItem) {
        dbItem.setName(itemReq.getName());
        dbItem.setDescription(itemReq.getDescription());
        dbItem.setToDoDate(LocalDate.parse(itemReq.getToDoDate()));
        dbItem.setUpdated(LocalDate.now());
        return itemRepository.save(dbItem);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return itemRepository.findById(id);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
