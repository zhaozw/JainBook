package org.jainbooks.ebook.authorization;

import javax.servlet.http.HttpServletRequest;

import org.jainbooks.ebook.domain.User;

public class BaseAuthorization {

	protected User baseAuthorization(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");

		return user;
	}
}
