/*package org.jainbooks.ebook.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jainbooks.ebook.domain.Logo;
import org.jainbooks.ebook.form.AddLogoForm;
import org.jainbooks.ebook.form.EditLogoForm;
import org.jainbooks.ebook.form.LogoStatusChangeForm;

public interface LogoService {
	public List<Logo> getAllActiveLogos() throws Exception;

	public void saveLogoForLogoType(Logo logo, long logoTypeId);

	public Logo getLogo(long id);

	public List<Logo> getAllLogos();

	public void addLogo(AddLogoForm addLogoForm) throws FileNotFoundException,
			IOException;

	public void updateLogo(EditLogoForm editLogoForm)
			throws FileNotFoundException, IOException;

	public List<Logo> getLogosBySearchString(StringBuilder searchString);

	public List<Logo> getAllLogosWithOrignalName();
	
	*//**
	 * Change the status of the logos. 
	 * @param changeForm
	 *//*
	public void updateLogoStatus(LogoStatusChangeForm changeForm);
}
*/