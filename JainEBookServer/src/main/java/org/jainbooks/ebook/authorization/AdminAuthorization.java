package org.jainbooks.ebook.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;

public class AdminAuthorization extends BaseAuthorization {

	private static final Logger logger = Logger
			.getLogger(AdminAuthorization.class);

	protected boolean adminAuthorization(HttpServletRequest request) {

		try {

			User user = super.baseAuthorization(request);

			if (user == null) {
				return false;
			}

			String userRole = user.getRole().getRoleType();
			if (userRole.equals("admin")) {
				return true;
			}

		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		}

		return false;
	}
}
