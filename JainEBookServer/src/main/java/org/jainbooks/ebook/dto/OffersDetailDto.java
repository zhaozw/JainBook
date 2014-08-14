package org.jainbooks.ebook.dto;

import java.io.Serializable;


public class OffersDetailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String offerId;
	private String description;
	private String validUpto;
	private String videoPath;
	private String couponPath;
	private String logoId;
	private String offerType;
	private String offerPath;
	private boolean active;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValidUpto() {
		return validUpto;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getCouponPath() {
		return couponPath;
	}

	public void setCouponPath(String couponPath) {
		this.couponPath = couponPath;
	}

	public String getLogoId() {
		return logoId;
	}

	public void setLogoId(String logoId) {
		this.logoId = logoId;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getOfferPath() {
		return offerPath;
	}

	public void setOfferPath(String offerPath) {
		this.offerPath = offerPath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
