package org.jainbooks.ebook.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleType;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_type", nullable = false, length = 20)
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String string) {
		this.roleType = string;
	}

}
