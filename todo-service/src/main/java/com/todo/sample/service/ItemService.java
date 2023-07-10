package com.todo.sample.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.todo.sample.entity.Item;

import java.util.List;

/**
 * Created by Md. Zohir Raihan on 5/21/2023.
 */
@Stateless
@LocalBean
public class ItemService implements EntityService<Item> {

	@PersistenceContext
	EntityManager entityManager;

	public void create(Item item) {
		entityManager.persist(item);
	}

	public Item find(int id) {
		return entityManager.find(Item.class, id);
	}

	public List<Item> find() {
		return entityManager.createNamedQuery(Item.FIND_ALL).getResultList();
	}

	public Item update(Item item) {
		return entityManager.merge(item);
	}

	public void delete(Item item) {
		entityManager.remove(item);
	}

}
