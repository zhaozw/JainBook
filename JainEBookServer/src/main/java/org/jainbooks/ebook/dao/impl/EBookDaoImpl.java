package org.jainbooks.ebook.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jainbooks.ebook.dao.EBookDao;
import org.jainbooks.ebook.domain.Category;
import org.jainbooks.ebook.domain.EBook;
import org.jainbooks.ebook.domain.UserLibrary;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Repository;

@Repository("ebookDao")
public class EBookDaoImpl extends GenericDaoImpl<EBook, Long> implements
		EBookDao {
	private static final Logger logger = Logger.getLogger(EBookDaoImpl.class);

	public List<EBook> getEBookLibrary() {
		Session session = null;
		List<EBook> ebooks = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from EBook where deleted=:deleted order by category";
			Query query = session.createQuery(queryString);
			query.setBoolean("deleted", false);
			ebooks = query.list();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return ebooks;
	}

	public List<EBook> getEBooksForCategory(Category category) {
		Session session = null;
		List<EBook> ebooks = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from EBook where deleted=:deleted and category_id=:category_id";
			Query query = session.createQuery(queryString);
			query.setBoolean("deleted", false);
			query.setInteger("category_id", category.getId());
			ebooks = query.list();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return ebooks;
	}

	public List<UserLibrary> getUserLibrary(String email) {
		Session session = null;
		List<UserLibrary> userLibrary = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from UserLibrary ul where ul.user.email=:email";
			Query query = session.createQuery(queryString);
			query.setString("email", email);
			userLibrary = query.list();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return userLibrary;
	}

	public EBook getEBookById(int id) {
		Session session = null;
		EBook ebook = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from EBook where id = :id";
			Query query = session.createQuery(queryString);
			query.setInteger("id", id);
			ebook = (EBook) query.uniqueResult();
		} catch (Exception e) {
			logger.fatal("ID: " + id + " "
					+ JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return ebook;
	}

	public UserLibrary saveUserLibrary(UserLibrary userLibrary) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(userLibrary);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			logger.fatal("userLibrary: " + userLibrary + " "
					+ JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.flush();
			session.close();
		}
		return userLibrary;

	}
}
