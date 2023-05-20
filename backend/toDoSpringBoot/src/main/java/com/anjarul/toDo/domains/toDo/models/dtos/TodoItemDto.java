package com.anjarul.toDo.domains.toDo.models.dtos;


import com.anjarul.toDo.domains.base.models.dtos.BaseDto;
import com.anjarul.toDo.domains.toDo.models.enums.Priority;
import com.anjarul.toDo.domains.toDo.models.enums.Status;
import com.anjarul.toDo.helperUtilities.DateUtil;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class TodoItemDto extends BaseDto {
    @Size(min = 3, max = 255)
    private String name;

    private String description;

    private Date date;

    private Priority priority;

    private Status status;

    private String tags;

}
