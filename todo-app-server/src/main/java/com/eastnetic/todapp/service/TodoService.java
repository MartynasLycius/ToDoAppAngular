package com.eastnetic.todapp.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.eastnetic.todapp.model.ToDo;
import com.eastnetic.todapp.repository.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<ToDo> getAllToDos() {
		return todoRepository.findAll();
	}

	public ToDo getToDoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }
	
	public ToDo createToDo(ToDo toDo) {
		return todoRepository.save(toDo);
	}

	public ToDo updateToDo(ToDo toDo) {
		return todoRepository.save(toDo);
	}

	public void deleteToDo(Long id) {
		todoRepository.deleteById(id);
	}
}
