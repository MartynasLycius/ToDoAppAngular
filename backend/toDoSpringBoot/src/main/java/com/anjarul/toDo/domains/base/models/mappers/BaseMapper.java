package com.anjarul.toDo.domains.base.models.mappers;


import com.anjarul.toDo.domains.base.models.dtos.BaseDto;
import com.anjarul.toDo.domains.base.models.entities.BaseEntity;

public interface BaseMapper<T extends BaseEntity, S extends BaseDto> {
    S map(T entity);

    T map(S dto, T exEntity);
}
