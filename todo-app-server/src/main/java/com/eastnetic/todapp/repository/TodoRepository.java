package com.eastnetic.todapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eastnetic.todapp.model.ToDo;

public interface TodoRepository extends JpaRepository<ToDo, Long> {
}
