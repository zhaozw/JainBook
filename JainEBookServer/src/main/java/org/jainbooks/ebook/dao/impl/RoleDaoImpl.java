package org.jainbooks.ebook.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jainbooks.ebook.dao.RoleDao;
import org.jainbooks.ebook.domain.Role;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Repository;

;

@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

	@Override
	public Role getRoleByType(String type) {
		Session session = null;
		Role role = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Role where roleType = :type";
			Query query = session.createQuery(queryString);
			query.setString("type", type);
			role = (Role) query.uniqueResult();
		} catch (Exception e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			session.close();
		}
		return role;
	}

}
