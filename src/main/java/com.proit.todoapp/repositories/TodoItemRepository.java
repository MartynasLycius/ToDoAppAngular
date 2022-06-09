package com.proit.todoapp.repositories;

import com.proit.todoapp.entity.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by rana on 8/27/21.
 */
public interface TodoItemRepository extends PagingAndSortingAndSpecificationExecutorRepository<TodoItem> {

}
