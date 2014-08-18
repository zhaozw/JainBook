package com.jainbooks.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class UserLibrary {

	
	@Expose
	private List<EBook> ebooks = new ArrayList<EBook>();

	public List<EBook> getEbooks() {
	return ebooks;
	}

	public void setEbooks(List<EBook> ebooks) {
	this.ebooks = ebooks;
	}

	@Override
	public String toString() {
		return "UserLibrary [ebooks=" + ebooks + "]";
	}
}
