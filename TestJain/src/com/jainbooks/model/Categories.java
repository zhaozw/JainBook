package com.jainbooks.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Categories {

	@Expose
	private Category category;
	@Expose
	private List<EBook> ebooks = new ArrayList<EBook>();

	public Category getCategory() {
	return category;
	}

	public void setCategory(Category category) {
	this.category = category;
	}


	public List<EBook> getEbooks() {
	return ebooks;
	}

	public void setEbooks(List<EBook> ebooks) {
	this.ebooks = ebooks;
	}

	@Override
	public String toString() {
		return "Categoryes [category=" + category + ", ebooks=" + ebooks + "]";
	}

	

	
}
