package com.proit.todo.service;

import com.proit.todo.entity.TodoEntity;
import com.proit.todo.exception.ResourceNotFoundException;
import com.proit.todo.model.TodoRequest;
import com.proit.todo.model.TodoResponse;
import com.proit.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * TodoService Impl Class
 *
 * @author Badrul
 */
@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repository;

    /**
     * Get All TodoEntities
     *
     * @return List<TodoEntity>
     */
    public List<TodoResponse> getAllTodoEntities() {
        List<TodoResponse> responses = new ArrayList<>();

        repository.findAllByIsDeletedFalseOrderByUpdatedAtDesc().forEach(entity -> {
            responses.add(todoEntityToResponse(entity));
        });

        return responses;
    }

    /**
     * Create TodoEntity
     *
     * @param request of TodoRequest
     * @return TodoEntity
     */
    public TodoResponse save(TodoRequest request) {

        Date now = new Date();

        TodoEntity entity = new TodoEntity();

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setDate(request.getDate());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        entity.setIsDeleted(false);

        return todoEntityToResponse(repository.save(entity));
    }

    /**
     * delete a TodoEntity
     *
     * @param id
     */
    public void delete(UUID id) {
        TodoEntity entity = getTodoEntity(id);
        repository.delete(entity);
    }

    /**
     * Update TodoEntity
     *
     * @param request
     * @param id
     * @return TodoResponse
     */
    public TodoResponse update(TodoRequest request, UUID id) {

        TodoEntity entity = getTodoEntity(id);

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setDate(request.getDate());
        entity.setUpdatedAt(new Date());

        return todoEntityToResponse(repository.save(entity));
    }

    /**
     * get TodoEntity
     *
     * @param id entity id
     * @return TodoEntity
     */
    public TodoEntity getTodoEntity(UUID id) {
        Optional<TodoEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new ResourceNotFoundException("Todo not found for id: " + id);
        }
    }

    /**
     * get TodoEntity By Id
     *
     * @param id
     * @return
     */
    public TodoResponse getTodoEntityById(UUID id) {
        return todoEntityToResponse(getTodoEntity(id));
    }

    /**
     * TodoEntity To Response
     *
     * @param entity
     * @return TodoResponse
     */
    private TodoResponse todoEntityToResponse(TodoEntity entity) {

        TodoResponse response = new TodoResponse();

        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setDate(entity.getDate());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;
    }
}
