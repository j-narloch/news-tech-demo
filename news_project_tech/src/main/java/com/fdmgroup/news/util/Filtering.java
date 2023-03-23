package com.fdmgroup.news.util;

public class Filtering {
	
	private String category;
	
	private String owner;
	
	public Filtering(String category) {
		super();
		this.category = category;
	}
	
	public Filtering() {
		super();
		this.category = "";
		this.owner = "";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


}
