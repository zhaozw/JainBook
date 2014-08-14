package org.jainbooks.ebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_library")
public class UserLibrary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;

	private User user;

	private EBook ebook;

	private Date created;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ebook_id")
	public EBook getEBook() {
		return ebook;
	}

	public void setEBook(EBook ebook) {
		this.ebook = ebook;
	}

}
