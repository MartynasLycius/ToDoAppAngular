package com.proit.todo.controller;

import com.proit.todo.dto.ItemDto;
import com.proit.todo.entity.Item;
import com.proit.todo.exception.ResourceNotFoundException;
import com.proit.todo.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/items")
@Api(value = "Item API")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    @ApiOperation(value = "Item List")
    public List<Item> getItems(){
        return itemService.getItems();
    }

    @PostMapping
    @ApiOperation(value = "Save Item")
    public Item saveItem(@Valid @RequestBody ItemDto itemDto){
        return itemService.save(itemDto.convertToEntity());
    }

    @PutMapping("/{itemId}")
    @ApiOperation(value = "Update Item")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") Integer itemId,
                       @Valid @RequestBody ItemDto itemDto){
        Item dbItem = itemService.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item Id " + itemId + " not found"));
        Item item = itemService.update(itemDto, dbItem);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/{itemId}")
    @ApiOperation(value = "Get an Item")
    public ResponseEntity<Item> getItem(@PathVariable("itemId") Integer itemId){
        Item item = itemService.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item Id " + itemId + " not found"));
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{itemId}")
    @ApiOperation(value = "Delete an Item")
    public ResponseEntity<?> deleteItem(@PathVariable Integer itemId){
        Item item = itemService.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item Id " + itemId + " not found"));
        itemService.delete(item);
        return ResponseEntity.ok().build();
    }
}
