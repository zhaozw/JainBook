package com.jainbooks.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Store {
	@Expose
	private List<Categories> categories = new ArrayList<Categories>();

	public List<Categories> getCategories() {
	return categories;
	}

	public void setCategories(List<Categories> categories) {
	this.categories = categories;
	}

	public List<Category> getAllCategory() {
		List<Category> categorieList = new ArrayList<Category>();
		Iterator<Categories> iterator = categories.iterator();
		while (iterator.hasNext()) {
			Categories categories = iterator.next();
			Category category = categories.getCategory();
			categorieList.add(category);
		}

		return categorieList;

	}
	public List<EBook> getAllBooks() {
		List<EBook> eBookList = new ArrayList<EBook>();
		Iterator<Categories> iterator = categories.iterator();
		while (iterator.hasNext()) {
			Categories categories = iterator.next();
			eBookList.addAll(categories.getEbooks());
			
		}
       return eBookList;

	}
	
	@Override
	public String toString() {
		return "Store [categories=" + categories + "]";
	}

	

}
