package com.proit.todoapp.web.resource;

import com.proit.todoapp.entity.TodoItem;
import com.proit.todoapp.mapper.Response;
import com.proit.todoapp.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

/**
 * Created by rana on 8/27/21.
 */
@RestController
@RequestMapping("/api/to-do-item")
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> paginationList(@RequestParam( value = "pageNumber", required = false) Integer pageNumber) {
        if(pageNumber== null) {
            pageNumber = 1;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        Page<TodoItem> currentResults = todoItemRepository.findAll(pageRequest);
        return new ResponseEntity<Page>(currentResults, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Response> create(@Valid @RequestBody TodoItem todoItem) {
        try {
            todoItem = todoItemRepository.save(todoItem);
            return new ResponseEntity<Response>(new Response(todoItem), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response("Failed to save TodoItem."), HttpStatus.OK);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Response> edit(@PathVariable("id") Long id) {
        try {
            Optional<TodoItem> todoItem =  todoItemRepository.findById(id);
            if(todoItem.get() == null) {
                return new ResponseEntity<Response>(new Response("Object with given id Not Found or don't have access", null), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<Response>(new Response(todoItem.get()), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response("Failed to get Object", null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response> update(@Valid @RequestBody TodoItem newTodoItem, @PathVariable Long id) {
        Optional<TodoItem> todoItem =  todoItemRepository.findById(id);
        if(todoItem.get() == null) {
            return new ResponseEntity<Response>(new Response("TodoItem with given id Not Found or don't have access", null), HttpStatus.BAD_REQUEST);
        } else {
            TodoItem todoItemObject = todoItem.get();
                newTodoItem.setId(todoItemObject.getId());
                newTodoItem.setCreatedBy(todoItemObject.getCreatedBy());
                newTodoItem.setCreatedOn(todoItemObject.getCreatedOn());
                newTodoItem = todoItemRepository.save(newTodoItem);
                return new ResponseEntity<Response>(new Response(newTodoItem), HttpStatus.OK);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        try {
            Optional<TodoItem> todoItem =  todoItemRepository.findById(id);
            if(todoItem.get() == null) {
                return new ResponseEntity<Response>(new Response("TodoItem with given id Not Found or don't have access", null), HttpStatus.BAD_REQUEST);
            } else {
                todoItemRepository.delete(todoItem.get());
            }
            return new ResponseEntity<Response>(new Response(todoItem.get()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Response>(new Response("TodoItem with given id Not Found or don't have access", null), HttpStatus.BAD_REQUEST);
        }
    }

}
