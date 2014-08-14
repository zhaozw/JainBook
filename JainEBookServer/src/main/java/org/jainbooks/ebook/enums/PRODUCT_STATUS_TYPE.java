package org.jainbooks.ebook.enums;

import java.io.Serializable;

public enum PRODUCT_STATUS_TYPE implements Serializable {

	RESERVED("reserved"), UNRESERVED("unreserved"), PAID("paid");
	private final String status;

	private PRODUCT_STATUS_TYPE(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
