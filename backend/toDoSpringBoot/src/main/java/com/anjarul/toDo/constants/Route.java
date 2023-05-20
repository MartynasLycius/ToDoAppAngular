package com.anjarul.toDo.constants;

public class Route {

    public static final String API_V1 = "/api/v1";

    public static final String SEARCH_ALL_TODO_ITEMS = API_V1 + "/todos";
    public static final String SEARCH_TODO_ITEM_BY_ID = API_V1 + "/todos/{id}";

    public static final String CREATE_TODO_ITEM = API_V1 + "/todos/create";
    public static final String UPDATE_TODO_ITEM_BY_ID = API_V1 + "/todos/{id}/update";
    public static final String DELETE_TODO_ITEM = API_V1 + "/todos/{id}/delete";
}
