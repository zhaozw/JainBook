/*package org.jainbooks.ebook.dao;

import java.util.List;

import org.jainbooks.ebook.domain.Logo;

public interface LogoDao extends GenericDao<Logo, Long> {

	public List<Logo> getAllActiveLogos();

	public void saveLogoForLogoType(Logo logo, long logoTypeId);
	
	public Logo getLogo(long id);
	
	public List<Logo> getAllLogos(); 
	
	public List<Logo> getLogosBySearchString(StringBuilder searchString);
	
	*//**
	 * Method will set the status(active/Not active) each logo in @param logoIdList
	 * 
	 * @param statusToSet
	 *            Value will be either 1(active) or 0(not active)
	 * @param logoIdList
	 *//*
	public void updateLogoStatus(boolean statusToSet, List<Long> logoIdList);
}
*/