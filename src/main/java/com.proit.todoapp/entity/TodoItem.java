package com.proit.todoapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * Created by rana on 9/06/22.
 */

@Entity
@Table(name = "TO_DO_ITEMS")
public class TodoItem  extends Audit {

    @SequenceGenerator(name = "TO_DO_ITEM_SEQ_GENERATOR", sequenceName = "TO_DO_ITEM_ID_SEQ")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TO_DO_ITEM_SEQ_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @Size(min = 2, max = 30)
    @NotEmpty(message = "title is required.")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotEmpty(message = "description is required.")
    private String description;

    @Size(min = 2, max = 30)
    @NotEmpty(message = "status is required.")
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
