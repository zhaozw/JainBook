package org.jainbooks.ebook.service.impl;

import java.util.Date;

import org.jainbooks.ebook.dao.RoleDao;
import org.jainbooks.ebook.dao.UserDao;
import org.jainbooks.ebook.domain.Role;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.dto.UserValidationDTO;
import org.jainbooks.ebook.service.UserService;
import org.jainbooks.ebook.util.AuthTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public User saveUser(User user, String roleType) throws Exception {
		Role role = roleDao.getRoleByType(roleType);
		user.setRole(role);
		user.setCreated(new Date());
		user.setUpdated(new Date());
		AuthTokenGenerator authTokenGenerator = new AuthTokenGenerator();
		user.setAuthenticationToken(authTokenGenerator.getAuthToken());
		return userDao.saveUser(user);
	}

	@Override
	public User updateUser(User user) throws Exception {
		user.setUpdated(new Date());
		return userDao.saveUser(user);
	}

	@Override
	public User getUserByEmail(String email) {

		return userDao.getUserByEmail(email);
	}

	@Override
	public User authinticateUser(String email, String password) {
		return userDao.authinticateUser(email, password);
	}

	@Override
	public UserValidationDTO validateUser(User user) {
		UserValidationDTO userValidationDTO = null;
		if (null == user) {
			userValidationDTO = new UserValidationDTO();
			userValidationDTO.setMessage("User data is not present");
			userValidationDTO.setStatusCode("ERROR_001");
		} else if (null == user.getEmail()) {
			userValidationDTO = new UserValidationDTO();
			userValidationDTO.setMessage("Email is not present");
			userValidationDTO.setStatusCode("ERROR_002");
		} else if (null == user.getLoginSource()) {
			userValidationDTO = new UserValidationDTO();
			userValidationDTO.setMessage("LoginSource is not present");
			userValidationDTO.setStatusCode("ERROR_003");
		} else if (user.getLoginSource().equals("direct")
				&& null == user.getPassword()) {
			userValidationDTO = new UserValidationDTO();
			userValidationDTO.setMessage("Password is not present");
			userValidationDTO.setStatusCode("ERROR_004");
		}
		return userValidationDTO;
	}

}
