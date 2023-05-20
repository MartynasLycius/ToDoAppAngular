package com.anjarul.toDo.domains.toDo.controllers;


import com.anjarul.toDo.domains.toDo.models.dtos.TodoItemDto;
import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import com.anjarul.toDo.domains.toDo.models.mappers.TodoItemMapper;
import com.anjarul.toDo.domains.toDo.services.TodoItemService;
import com.anjarul.toDo.exceptions.NotFoundException;
import com.anjarul.toDo.constants.Route;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoItemController {
    private final TodoItemService todoItemService;
    private final TodoItemMapper todoItemMapper;

    @Autowired
    public TodoItemController(TodoItemService todoItemService, TodoItemMapper todoItemMapper) {
        this.todoItemService = todoItemService;
        this.todoItemMapper = todoItemMapper;
    }

    @GetMapping(Route.SEARCH_ALL_TODO_ITEMS)
    public ResponseEntity<Page<TodoItem>> search(@RequestParam(value = "query", defaultValue = "") String query,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "0") int size) {
        Page<TodoItem> items = this.todoItemService.search(query, page, size);

        return ResponseEntity.ok(items);
    }

    @GetMapping(Route.SEARCH_TODO_ITEM_BY_ID)
    public ResponseEntity<TodoItem> find(@PathVariable("id") Long id) throws NotFoundException {
        TodoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(item);
    }


    @PostMapping(Route.CREATE_TODO_ITEM)
    public ResponseEntity<TodoItem> create(@Valid @RequestBody TodoItemDto dto) {
        TodoItem item = this.todoItemMapper.map(dto, null);
        return ResponseEntity.ok(this.todoItemService.save(item));
    }


    @PutMapping(Route.UPDATE_TODO_ITEM_BY_ID)
    public ResponseEntity<TodoItem> update(@PathVariable("id") Long id,
                                           @Valid @RequestBody TodoItemDto dto) throws NotFoundException {
        TodoItem item = this.todoItemService.find(id).orElseThrow(NotFoundException::new);
        item = this.todoItemMapper.map(dto, item);
        return ResponseEntity.ok(this.todoItemService.save(item));
    }

    @DeleteMapping(Route.DELETE_TODO_ITEM)
    public ResponseEntity<String> delete(@PathVariable("id") Long id,
                                         @RequestParam(value = "soft_delete", defaultValue = "true") boolean softDelete) throws NotFoundException {
        this.todoItemService.delete(id, softDelete);
        return new ResponseEntity<>("Item is Deleted", null, 200);
    }
}
