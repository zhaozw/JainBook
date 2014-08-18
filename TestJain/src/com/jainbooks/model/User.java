package com.jainbooks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
	@Expose
	private String authToken;
	@Expose
	private String name;
	@Expose
	private String loginFrom;
	@Expose
	private String email;
	
	public String getAuthToken() {
	return authToken;
	}

	public void setAuthToken(String authToken) {
	this.authToken = authToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	}

