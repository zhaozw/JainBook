package org.jainbooks.ebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ebook")
public class EBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;

	@Column(name = "title", nullable = false, length = 150, unique = true)
	private String title;

	@Column(name = "author", nullable = false, length = 70)
	private String author;

	@Column(name = "summary", length = 200)
	private String summary;

	@Column(name = "deleted", nullable = false, columnDefinition = "tinyint default false")
	private Boolean deleted = false;

	@Column(name = "rating")
	private int rating;

	@Column(name = "thumbnail_url")
	private String thumbnailUrl;

	@ManyToOne()
	@JoinColumn(name = "category_id",  nullable = false)
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "EBook [id=" + id + ", created=" + created + ", updated="
				+ updated + ", title=" + title + ", author=" + author
				+ ", summary=" + summary + ", deleted=" + deleted + ", rating="
				+ rating + ", thumbnailUrl=" + thumbnailUrl + ", category="
				+ category + "]";
	}

}
