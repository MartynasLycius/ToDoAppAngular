package com.anjarul.toDo.domains.toDo.models.entities;


import com.anjarul.toDo.domains.base.models.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "todo_items")
public class TodoItem extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    private String priority;

    @Column(nullable = false)
    private String status;

    private String tags;
}
