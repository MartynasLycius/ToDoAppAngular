package com.anjarul.toDo.domains.base.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDto implements Serializable {
    @JsonProperty("id")
    Long id;

    @JsonProperty(value = "created_at", access = JsonProperty.Access.READ_ONLY)
    Date createdAt;

    @JsonProperty(value = "updated_at", access = JsonProperty.Access.READ_ONLY)
    Date updatedAt;

}
