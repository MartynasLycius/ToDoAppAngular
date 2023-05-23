package com.proit.todo.dto;

import com.proit.todo.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class ItemDto {
    private Integer id;
    @NotEmpty(message = "*Please provide name")
    private String name;
    private String description;
    @NotEmpty(message = "*Date can not be empty")
    private String toDoDate;

    public Item convertToEntity(){
        LocalDate date = LocalDate.parse(toDoDate);
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setToDoDate(date);
        return item;
    }
}
