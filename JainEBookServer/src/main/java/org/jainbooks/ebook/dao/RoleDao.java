package org.jainbooks.ebook.dao;

import org.jainbooks.ebook.domain.Role;

public interface RoleDao extends GenericDao<Role, Long> {
	
	public Role getRoleByType(String Type);
}
