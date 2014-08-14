package org.jainbooks.ebook.dto;

import java.util.List;

import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;

public class CategoryDTO {

	private Category category;

	private List<EBook> ebooks;

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
		return "CategoryDTO [category=" + category + ", ebooks=" + ebooks + "]";
	}

}
