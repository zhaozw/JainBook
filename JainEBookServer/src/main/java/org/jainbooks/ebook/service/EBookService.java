package org.jainbooks.ebook.service;

import java.util.List;
import java.util.Map;

import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;
import org.jainbooks.ebook.dto.CategoryDTO;

public interface EBookService {
	public List<EBook> getEBookLibrary();

	public Map<Category, List<EBook>> getEBooksByCategory();

	public CategoryDTO getEBooksForCategory(String categoryId);

	public List<EBook> getUserLibrary(String email);

	public void addEBookToUserLibrary(String email, int ebookId);
}
