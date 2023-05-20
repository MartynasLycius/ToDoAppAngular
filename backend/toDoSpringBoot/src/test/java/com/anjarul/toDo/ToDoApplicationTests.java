package com.anjarul.toDo;

import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import com.anjarul.toDo.domains.toDo.repositories.TodoItemRepository;
import com.anjarul.toDo.domains.toDo.services.TodoItemService;
import com.anjarul.toDo.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ToDoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TodoItemRepository repository;

    @Test
    public void createTodoTest() {
        TodoItem entity = new TodoItem();
        entity.setName("Finish project");
        entity.setDescription("Complete the remaining tasks for the project");

        entity.setDate(new Date());
        entity.setPriority("LOW");
        entity.setStatus("PENDING");
        entity.setTags("Test");
        TodoItem savedTodo = repository.save(entity);

        assertNotNull(savedTodo.getId());
        assertEquals("Finish project", savedTodo.getName());
        assertEquals("Complete the remaining tasks for the project", savedTodo.getDescription());
        assertEquals("PENDING", savedTodo.getStatus());
        this.getTodoByIdTest(entity.getId());
    }

//    @Test
    public void getTodoByIdTest(Long id) {
        TodoItem todo = repository.getTodoById(id);
        assertNotNull(todo);
        this.updateTodoTest(id);
    }

//    @Test
    public void updateTodoTest(Long id) {
        // Assuming there is a Todo with ID 1 in the database
        TodoItem entity = repository.getTodoById(id);

        entity.setStatus("IN_PROGRESS");
        entity.setDate(new Date());

        TodoItem updatedTodo = repository.save(entity);

        assertEquals("IN_PROGRESS", updatedTodo.getStatus());
        assertNotNull(updatedTodo.getDate());
        this.deleteTodoTest(id);
    }

//    @Test
    public void deleteTodoTest(Long id)  {
        // Assuming there is a Todo with ID 1 in the database
        TodoItem todo = repository.getTodoById(id);
        repository.delete(todo);

        // Assert that the todo has been deleted
        assertNull(repository.getTodoById(id));
    }

}
