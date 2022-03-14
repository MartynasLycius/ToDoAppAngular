package com.proit.todo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
public class TodoRequest {

    @Length(max = 255)
    private String name;

    @Length(max = 500)
    private String description;

    private Date date;

}
