package org.jainbooks.ebook.enums;

public enum IMAGE_TYPE {

	BRAND_LOGO("brand_logo"), OFFER_IMAGE("offer_image"), OFFER_COUPON("offer_coupon");

	private final String imageType;

	private IMAGE_TYPE(String imageType) {
		this.imageType = imageType;
	}

	public String getImageType() {
		return imageType;
	}
}
