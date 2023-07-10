package com.todo.sample.service;

import java.util.List;

/**
 * Created by Md. Zohir Raihan on 5/21/2023.
 *
 * Interface that used only for naming convention for methods for services
 */
public interface EntityService<E> {

    void create(E e);

    E find(int id);

    E update(E e);

    void delete(E e);

    List<E> find();

}
