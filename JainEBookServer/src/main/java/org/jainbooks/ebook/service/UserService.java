package org.jainbooks.ebook.service;

import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.dto.UserValidationDTO;

public interface UserService {

	public User saveUser(User user, String roleType) throws Exception;
	
	public UserValidationDTO validateUser(User user);

	public User updateUser(User user) throws Exception;
	
	public User getUserByEmail(String email);
	
	public User authinticateUser(String email, String password);
}
