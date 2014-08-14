package org.jainbooks.ebook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jainbooks.ebook.dao.CategoryDao;
import org.jainbooks.ebook.dao.EBookDao;
import org.jainbooks.ebook.dao.UserDao;
import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.domain.UserLibrary;
import org.jainbooks.ebook.dto.CategoryDTO;
import org.jainbooks.ebook.service.EBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ebookService")
public class EBookServiceImpl implements EBookService {

	@Autowired
	private EBookDao ebookDao;

	public void setEbookDao(EBookDao ebookDao) {
		this.ebookDao = ebookDao;
	}

	public List<EBook> getEBookLibrary() {
		return ebookDao.getEBookLibrary();
	}

	@Autowired
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Map<Category, List<EBook>> getEBooksByCategory() {

		Map<Category, List<EBook>> categoriesMap = new HashedMap();
		List<EBook> ebooks = ebookDao.getEBookLibrary();

		for (EBook eBook : ebooks) {

			if (!categoriesMap.containsKey(eBook.getCategory())) {
				List<EBook> categoryEBooks = new ArrayList<EBook>();
				categoryEBooks.add(eBook);
				categoriesMap.put(eBook.getCategory(), categoryEBooks);
			} else {
				List<EBook> categoryEBooks = categoriesMap.get(eBook
						.getCategory());
				categoryEBooks.add(eBook);
				categoriesMap.put(eBook.getCategory(), categoryEBooks);
			}

		}
		return categoriesMap;
	}

	public CategoryDTO getEBooksForCategory(String categoryId) {
		CategoryDTO categoryDTO = null;
		Category category = categoryDao
				.getCategoryById(new Integer(categoryId));

		List<EBook> ebooks = ebookDao.getEBooksForCategory(category);
		if (null != ebooks && null != category) {
			categoryDTO = new CategoryDTO();
			categoryDTO.setCategory(category);
			categoryDTO.setEbooks(ebooks);
		}

		return categoryDTO;
	}

	public List<EBook> getUserLibrary(String email) {
		List<UserLibrary> userLibraries = ebookDao.getUserLibrary(email);
		List<EBook> ebooks = new ArrayList<>();
		if (null != userLibraries) {
			for (UserLibrary userLibrary : userLibraries) {
				ebooks.add(userLibrary.getEBook());
			}
		}
		return ebooks;
	}

	public void addEBookToUserLibrary(String email, int ebookId) {

		User user = userDao.getUserByEmail(email);

		if (null == user) {
			return;
		}
		EBook ebook = ebookDao.getEBookById(ebookId);

		if (null == ebook) {
			return;
		}

		UserLibrary userLibrary = new UserLibrary();
		userLibrary.setCreated(new Date());
		userLibrary.setEBook(ebook);
		userLibrary.setUser(user);
		ebookDao.saveUserLibrary(userLibrary);
		
	}

}
