package com.filestash.domain;

import org.springframework.stereotype.Component;

@Component
public class Category {
	
	private int owner;
	private String name;
	private String color;
	
	public Category() {}
	
	public Category(int owner, String name, String color) {
		super();
		this.owner = owner;
		this.name = name;
		this.color = color;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
