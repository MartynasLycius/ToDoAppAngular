package com.anjarul.toDo.domains.toDo.models.mappers;

import com.anjarul.toDo.domains.base.models.mappers.BaseMapper;
import com.anjarul.toDo.domains.toDo.models.dtos.TodoItemDto;
import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import com.anjarul.toDo.domains.toDo.models.enums.Priority;
import com.anjarul.toDo.domains.toDo.models.enums.Status;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class TodoItemMapper implements BaseMapper<TodoItem, TodoItemDto> {

    @Override
    public TodoItemDto map(TodoItem entity) {
        Objects.requireNonNull(entity);
        TodoItemDto dto = new TodoItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPriority(Priority.get(entity.getPriority()));
        dto.setStatus(Status.valueOf(entity.getStatus()));
        dto.setTags(entity.getTags());

        dto.setDate(entity.getDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    @Override
    public TodoItem map(TodoItemDto dto, TodoItem exEntity) {
        TodoItem entity = exEntity;
        if (entity == null) entity = new TodoItem();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate() == null ? new Date() : dto.getDate());
        entity.setPriority(dto.getPriority() == null ? Priority.NORMAL.name() : dto.getPriority().name());
        entity.setStatus(dto.getStatus() == null ? Status.PENDING.name() :dto.getStatus().name());
        entity.setTags(dto.getTags());

        return entity;
    }
}
