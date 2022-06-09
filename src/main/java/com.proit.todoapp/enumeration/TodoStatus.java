package com.proit.todoapp.enumeration;

/**
 * Created by rana on 9/06/22.
 */
public enum TodoStatus {
	OPEN("OPEN"),
	IN_PROGRESS("IN_PROGRESS"),
	CLOSED("CLOSED");
	private String text;

	TodoStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
