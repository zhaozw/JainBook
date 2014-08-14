package org.jainbooks.ebook.enums;

import java.io.Serializable;

public enum OFFER_TYPE implements Serializable {

	REGULAR("Regular"), FEATURED("Featured");

	private final String offerType;

	private OFFER_TYPE(String offerType) {
		this.offerType = offerType;
	}

	public String getOfferType() {
		return offerType;
	}
}
