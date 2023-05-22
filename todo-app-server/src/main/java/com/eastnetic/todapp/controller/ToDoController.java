package com.eastnetic.todapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eastnetic.todapp.model.ToDo;
import com.eastnetic.todapp.service.TodoService;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/todos")
public class ToDoController {
	private final TodoService todoService;

	public ToDoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping
	public List<ToDo> getAllToDos() {
		return todoService.getAllToDos();
	}

	@GetMapping("/{id}")
	public ToDo getToDoById(@PathVariable Long id) {
		return todoService.getToDoById(id);
	}

	@PostMapping
	public ToDo createToDo(@RequestBody ToDo toDo) {
		return todoService.createToDo(toDo);
	}

	@PutMapping("/{id}")
	public ToDo updateToDo(@PathVariable Long id, @RequestBody ToDo toDo) {
		ToDo existingToDo = todoService.getToDoById(id);
		existingToDo.setName(toDo.getName());
		existingToDo.setDescription(toDo.getDescription());
		existingToDo.setDate(toDo.getDate());
		return todoService.updateToDo(existingToDo);
	}

	@DeleteMapping("/{id}")
	public void deleteToDo(@PathVariable Long id) {
		todoService.deleteToDo(id);
	}
}
