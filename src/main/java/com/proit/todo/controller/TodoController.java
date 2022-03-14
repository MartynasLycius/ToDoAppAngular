package com.proit.todo.controller;

import com.proit.todo.model.TodoRequest;
import com.proit.todo.model.TodoResponse;
import com.proit.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public ResponseEntity<List<TodoResponse>> getAllTodoEntities() {
        return new ResponseEntity<>(todoService.getAllTodoEntities(), HttpStatus.OK);
    }

    @GetMapping("{todoId}")
    public ResponseEntity<TodoResponse> getTodoEntity(@PathVariable UUID todoId) {
        return new ResponseEntity<>(todoService.getTodoEntityById(todoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> save(@Valid @RequestBody TodoRequest request) {
        return new ResponseEntity<>(todoService.save(request), HttpStatus.OK);
    }

    @PutMapping("{todoId}")
    public ResponseEntity<TodoResponse> update(@Valid @RequestBody TodoRequest request, @PathVariable UUID todoId) {
        return new ResponseEntity<>(todoService.update(request, todoId), HttpStatus.OK);
    }

    @DeleteMapping("{todoId}")
    public void getAllTodoEntities(@PathVariable UUID todoId) {
        todoService.delete(todoId);
    }
}
