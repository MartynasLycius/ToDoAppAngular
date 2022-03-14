package com.proit.todo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class TodoResponse {
    private UUID id;
    private String name;
    private String description;
    private Date date;
    private Date createdAt;
    private Date updatedAt;
}
