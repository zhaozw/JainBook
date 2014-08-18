package com.jainbooks.model;

import com.google.gson.annotations.Expose;

public class EBook  {
	@Expose
	private Category category;
	@Expose
	private String created;
	@Expose
	private String updated;
	@Expose
	private String title;
	@Expose
	private String author;
	@Expose
	private Boolean deleted;
	@Expose
	private Integer rating;
	@Expose
	private String summary;
	@Expose
	private String thumbnailUrl;
	@Expose
	private Integer id;

	public Category getCategory() {
	return category;
	}

	public void setCategory(Category category) {
	this.category = category;
	}

	

	public String getCreated() {
	return created;
	}

	public void setCreated(String created) {
	this.created = created;
	}

	

	public String getUpdated() {
	return updated;
	}

	public void setUpdated(String updated) {
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


	public Integer getRating() {
	return rating;
	}

	public void setRating(Integer rating) {
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

	

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	@Override
	public String toString() {
		return "EBook [category=" + category + ", created=" + created
				+ ", updated=" + updated + ", title=" + title + ", author="
				+ author + ", deleted=" + deleted + ", rating=" + rating
				+ ", summary=" + summary + ", thumbnailUrl=" + thumbnailUrl
				+ ", id=" + id + "]";
	}

	

	}
