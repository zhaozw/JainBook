/*package org.jainbooks.ebook.form;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.jainbooks.ebook.domain.LogoType;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class EditLogoForm {

	@Min(value = 1, message = "Please select logo type")
	private Long logoType;

	private CommonsMultipartFile file;

	@NotEmpty(message = "Logo name must not be empty")
	private String logoName;

	private String imagePath;
	
	private List<LogoType> logoTypes;
	
	private Long logoId;
	
	public Long getLogoType() {
		return logoType;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoType(Long logoType) {
		this.logoType = logoType;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<LogoType> getLogoTypes() {
		return logoTypes;
	}

	public void setLogoTypes(List<LogoType> logoTypes) {
		this.logoTypes = logoTypes;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}
}
*/