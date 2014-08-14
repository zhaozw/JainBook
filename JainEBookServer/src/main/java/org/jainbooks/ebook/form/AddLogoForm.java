/*package org.jainbooks.ebook.form;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.jainbooks.ebook.custom.validator.FileNotEmpty;
import org.jainbooks.ebook.custom.validator.FileTypeMatch;
import org.jainbooks.ebook.domain.LogoType;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AddLogoForm {

	@Min(value = 1, message = "Please select logo type")
	private Long logoType;

	@FileNotEmpty
	@FileTypeMatch
	private CommonsMultipartFile file;

	@NotEmpty(message = "Logo name must not be empty")
	private String logoName;

	private List<LogoType> logoTypes;

	public Long getLogoType() {
		return logoType;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setLogoType(Long logoType) {
		this.logoType = logoType;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public List<LogoType> getLogoTypes() {
		return logoTypes;
	}

	public void setLogoTypes(List<LogoType> logoTypes) {
		this.logoTypes = logoTypes;
	}
}
*/