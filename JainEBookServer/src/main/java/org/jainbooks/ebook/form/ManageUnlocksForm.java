package org.jainbooks.ebook.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ManageUnlocksForm {

	@NotEmpty
	private String fromDate;
	
	@NotEmpty
	private String toDate;

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
