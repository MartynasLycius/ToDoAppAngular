package com.proit.todoapp.enumeration;

/**
 * Created by rana on 9/06/22.
 */

public enum Role {
	ROLE_ADMIN("ADMIN"),
	ROLE_USER("USER");
	private String text;

	Role(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
