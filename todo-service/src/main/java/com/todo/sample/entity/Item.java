package com.todo.sample.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Md. Zohir Raihan on 5/21/2023.
 */

@XmlRootElement
@Entity
@Table(name = "items")
@NamedQuery(name = Item.FIND_ALL, query = "select i from Item i")
public class Item {

    public static final String FIND_ALL = "Item.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    private String description;
    
    private Date date;
    
    private boolean isUrgent;
    
    public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Item() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", isUrgent="
				+ isUrgent + "]";
	}

//	@Override
//	public String toString() {
//		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + "]";
//	} 
	
	
    
}
