package org.jainbooks.ebook.dao;

import org.jainbooks.ebook.domain.User;

public interface UserDao extends GenericDao<User, Long> {

	public User saveUser(User user);
	
	public User getUserByEmail(String email);

	public User authinticateUser(String email, String password);
}
