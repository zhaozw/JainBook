package com.jainbooks.model;

import com.google.gson.annotations.Expose;

public class TALoginResponse {

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

		if (loginData.getError() != null) {
			return false;
		} else if (loginData.getStatus() != null
				&& loginData.getStatus().equalsIgnoreCase("success")) {
			return true;
		}

		return false;
	}

}
