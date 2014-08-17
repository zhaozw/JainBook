package com.jainbooks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
	@Expose
	private String authToken;
	@Expose
	private String statusCode;
	@Expose
	private String message;

	public String getAuthToken() {
	return authToken;
	}

	public void setAuthToken(String authToken) {
	this.authToken = authToken;
	}

	public String getStatusCode() {
	return statusCode;
	}

	public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
	}

	public String getMessage() {
	return message;
	}

	public void setMessage(String message) {
	this.message = message;
	}
}
