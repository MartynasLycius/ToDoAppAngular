package com.proit.todo.service;

import com.proit.todo.entity.TodoEntity;
import com.proit.todo.model.TodoRequest;
import com.proit.todo.model.TodoResponse;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    List<TodoResponse> getAllTodoEntities();

    TodoResponse save(TodoRequest request);

    void delete(UUID id);

    TodoResponse update(TodoRequest request, UUID id);

    TodoResponse getTodoEntityById(UUID id);
}
