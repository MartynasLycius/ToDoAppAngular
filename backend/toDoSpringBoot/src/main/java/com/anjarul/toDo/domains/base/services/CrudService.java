package com.anjarul.toDo.domains.base.services;

import com.anjarul.toDo.domains.base.models.entities.BaseEntity;
import com.anjarul.toDo.exceptions.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CrudService<T extends BaseEntity> {
    Page<T> search(String query, int page, int size);

    T save(T entity);

    Optional<T> find(Long id);

    void delete(Long id, Boolean softDelete) throws NotFoundException;
}