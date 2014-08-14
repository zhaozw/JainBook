package org.jainbooks.ebook.dto;

public class AddUserEBookDTO {

	private String email;

	private int ebookId;

	private String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEbookId() {
		return ebookId;
	}

	public void setEbookId(int ebookId) {
		this.ebookId = ebookId;
	}

}
