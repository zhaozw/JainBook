package org.jainbooks.ebook.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgetPasswordForm {

	@NotEmpty(message = "email must not be empty")
	@Email(message = "email format is incorrect")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
