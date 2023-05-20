package com.anjarul.toDo.domains.toDo.repositories;

import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    @Query("SELECT t FROM TodoItem t WHERE (:q IS NULL OR LOWER(t.name) LIKE %:q%) AND t.deleted=false")
    Page<TodoItem> search(@Param("q") String query, Pageable pageable);

    @Query("SELECT t FROM TodoItem t WHERE t.id=:id AND t.deleted=false")
    Optional<TodoItem> find(@Param("id") Long id);

    @Query("SELECT t FROM TodoItem t WHERE t.id=:id AND t.deleted=false")
    TodoItem getTodoById(@Param("id") Long id);
}
