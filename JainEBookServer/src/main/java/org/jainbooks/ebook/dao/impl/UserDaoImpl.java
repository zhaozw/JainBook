package org.jainbooks.ebook.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jainbooks.ebook.dao.UserDao;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Override
	public User saveUser(User user) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			logger.fatal("User: " + user + " "
					+ JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.flush();
			session.close();
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = null;
		User user = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from User where email = :email";
			Query query = session.createQuery(queryString);
			query.setString("email", email);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			logger.fatal("Email: " + email + " "
					+ JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User authinticateUser(String email, String password) {
		Session session = null;
		User user = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from User where email = :email and password = :pwd";
			Query query = session.createQuery(queryString);
			query.setString("email", email);
			query.setString("pwd", password);
			user = (User) query.uniqueResult();
		} catch (Exception e) {
			logger.info("Login falied for Email=" + email + " and Password="
					+ "" + password);
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return user;
	}

}
