package com.proit.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate toDoDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate created = LocalDate.now();
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate updated;
}
