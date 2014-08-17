package com.jainbooks.model;

import com.google.gson.annotations.Expose;

public class LoginResponse {

	@Expose
	private Login login;

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public boolean getLoginStatus() {

		Login loginData = getLogin();

		 if (loginData.getStatusCode()!= null
				&& loginData.getStatusCode().equalsIgnoreCase("SUCCESS_003")) {
			return true;
		}

		return false;
	}

}
