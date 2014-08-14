package org.jainbooks.ebook.authorization;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.domain.User;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;

public class UserAuthorization extends BaseAuthorization {

	private static final Logger logger = Logger
			.getLogger(UserAuthorization.class);

	protected boolean userAuthorization(HttpServletRequest request) {

		try {

			User user = super.baseAuthorization(request);

			if (user == null) {
				return false;
			}

		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		}

		return true;
	}
}
