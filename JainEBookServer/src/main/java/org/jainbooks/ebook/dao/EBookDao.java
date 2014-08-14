package org.jainbooks.ebook.dao;

import java.util.List;

import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;
import org.jainbooks.ebook.domain.UserLibrary;

public interface EBookDao {
	public List<EBook> getEBookLibrary();

	public List<EBook> getEBooksForCategory(Category category);

	public List<UserLibrary> getUserLibrary(String email);
	
	public EBook getEBookById(int id);
	
	public UserLibrary saveUserLibrary(UserLibrary userLibrary);
}
