package com.anjarul.toDo.domains.toDo.services;


import com.anjarul.toDo.domains.base.services.CrudService;
import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;

public interface TodoItemService extends CrudService<TodoItem> {

    TodoItem getTodoById(Long l);
}
