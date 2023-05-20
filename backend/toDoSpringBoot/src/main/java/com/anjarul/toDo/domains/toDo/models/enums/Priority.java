package com.anjarul.toDo.domains.toDo.models.enums;

public enum Priority {
    HIGH,
    NORMAL,
    LOW;

    public static Priority get(String priority) {
        for (Priority item : Priority.values()) {
            if (item.name().contains(priority)) return item;
        }
        return null;
    }
}