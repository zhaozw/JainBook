package org.jainbooks.ebook.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jainbooks.ebook.dao.CategoryDao;
import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Repository;

@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao{

	private static final Logger logger = Logger.getLogger(CategoryDaoImpl.class);

	@Override
	public Category getCategoryById(int id) {
		Session session = null;
		Category category = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Category where id = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			category = (Category) query.uniqueResult();
		} catch (Exception e) {
			logger.fatal("ID: " + id + " "
					+ JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return category;
	}

}
