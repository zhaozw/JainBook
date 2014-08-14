package org.jainbooks.ebook.form;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jainbooks.ebook.custom.validator.DateFormatValidator;
import org.jainbooks.ebook.custom.validator.DateValidator;
import org.jainbooks.ebook.custom.validator.FileNotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AddOfferForm {

	private Long logoId; 
	
	@FileNotEmpty
	private CommonsMultipartFile offerFile; 
	
	@FileNotEmpty
	private CommonsMultipartFile couponFile;
	
	private String videoPath;
	
	@DateValidator
	@DateFormatValidator
	private String validityDate;
	
	@NotEmpty
	private String offerType;
	
	@Length(max=50)
	private String description;
	
	private List<String> offerTypes;

	public CommonsMultipartFile getOfferFile() {
		return offerFile;
	}

	public CommonsMultipartFile getCouponFile() {
		return couponFile;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferFile(CommonsMultipartFile offerFile) {
		this.offerFile = offerFile;
	}

	public void setCouponFile(CommonsMultipartFile couponFile) {
		this.couponFile = couponFile;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public List<String> getOfferTypes() {
		return offerTypes;
	}

	public void setOfferTypes(List<String> offerTypes) {
		this.offerTypes = offerTypes;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
