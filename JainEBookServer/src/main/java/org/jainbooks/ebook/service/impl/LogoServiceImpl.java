/*package org.jainbooks.ebook.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.dao.LogoDao;
import org.jainbooks.ebook.domain.Logo;
import org.jainbooks.ebook.enums.IMAGE_TYPE;
import org.jainbooks.ebook.form.AddLogoForm;
import org.jainbooks.ebook.form.EditLogoForm;
import org.jainbooks.ebook.form.LogoStatusChangeForm;
import org.jainbooks.ebook.service.LogoService;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("logoService")
public class LogoServiceImpl implements LogoService {

	private static final Logger logger = Logger
			.getLogger(LogoServiceImpl.class);

	@Autowired
	private LogoDao logoDao;

	public LogoDao getLogoDao() {
		return logoDao;
	}

	public void setLogoDao(LogoDao logoDao) {
		this.logoDao = logoDao;
	}

	@Override
	public List<Logo> getAllActiveLogos() throws Exception {
		return appendServerUrlToLogos(logoDao.getAllActiveLogos());
	}

	@Override
	public void saveLogoForLogoType(Logo logo, long logoTypeId) {
		logoDao.saveLogoForLogoType(logo, logoTypeId);
	}

	@Override
	public Logo getLogo(long id) {
		return logoDao.getLogo(id);
	}

	@Override
	public List<Logo> getAllLogos() {
		return logoDao.getAllLogos();
	}

	@Override
	public void updateLogo(EditLogoForm editLogoForm)
			throws FileNotFoundException, IOException {

		String imageName = null;
		String imageLocationOnServer = null;

		Logo logo = getLogo(editLogoForm.getLogoId());
		logo.setName(editLogoForm.getLogoName());

		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) editLogoForm
				.getFile();

		if (!commonsMultipartFile.getOriginalFilename().equals("")) {
			imageName = JainBookUtil.uploadImageOnServer(commonsMultipartFile,
					IMAGE_TYPE.BRAND_LOGO);
			imageLocationOnServer = PropertiesFileReaderUtil
					.getApplicationProperty("image.public.url") + "/" + imageName;
			logo.setImagePath(imageLocationOnServer);
		}

		saveLogoForLogoType(logo, editLogoForm.getLogoType());
	}

	@Override
	public void addLogo(AddLogoForm addLogoForm) throws FileNotFoundException,
			IOException {

		String imageName = null;
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) addLogoForm
				.getFile();

		imageName = JainBookUtil.uploadImageOnServer(commonsMultipartFile,
				IMAGE_TYPE.BRAND_LOGO);

		
		 * String imageLocationOnServer = PropertiesFileReaderUtil
		 * .getPropertyValue("serverPath") + "/" + imageName;
		 

		String imageLocationOnServer = PropertiesFileReaderUtil
				.getApplicationProperty("image.public.url") + "/" + imageName;

		String logoName = addLogoForm.getLogoName();
		Long type = addLogoForm.getLogoType();

		Logo logo = new Logo();
		logo.setImagePath(imageLocationOnServer);
		logo.setActive(true);
		logo.setName(JainBookUtil.generateUniqueLogoName(logoName));

		saveLogoForLogoType(logo, type);
	}

	@Override
	public List<Logo> getLogosBySearchString(StringBuilder searchString) {
		return setOrignalLogoNames(logoDao.getLogosBySearchString(searchString));
	}

	@Override
	public List<Logo> getAllLogosWithOrignalName() {

		logger.info("Inside getAllLogosWithOrignalName");

		return setOrignalLogoNames(logoDao.getAllLogos());

	}

	private List<Logo> setOrignalLogoNames(List<Logo> list) {

		logger.info("Inside setOrignalLogoNames");

		List<Logo> newList = new ArrayList<Logo>();

		for (Logo logo : list) {
			logo.setName(JainBookUtil.getOrignalLogoName(logo.getName()));
			logo.setImagePath(JainBookUtil.appendServerUrlToPath(logo
					.getImagePath()));
			newList.add(logo);
		}
		return newList;
	}

	*//**
	 * Method will append server url to all image paths
	 * 
	 * @param list
	 * @return
	 *//*
	private List<Logo> appendServerUrlToLogos(List<Logo> list) {

		logger.info("Inside appendServerUrlToLogos");

		List<Logo> newList = new ArrayList<Logo>();

		for (Logo logo : list) {
			logo.setImagePath(JainBookUtil.appendServerUrlToPath(logo
					.getImagePath()));
			newList.add(logo);
		}
		return newList;
	}
	
	
	
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yourkey.service.LogoService#updateLogoStatus(com.yourkey.web.form
	 * .LogoStatusChangeForm)
	 
	public void updateLogoStatus(LogoStatusChangeForm changeForm) {
		String action = changeForm.getAction();
		String logoList[] = changeForm.getLogoList();
		boolean statusToSet=false;

		List<Long> logoIdList = new ArrayList<Long>();

		for (String str : logoList) {
			logoIdList.add(new Long(str));
		}

		// check whether a comma exists or not
		int index = action.indexOf(',');
		
		if(index != -1 && action.substring(0, index).equals("activate")) {
			statusToSet = true;
		} else if(action.equals("activate")){
			statusToSet = true;
		}
		
		logoDao.updateLogoStatus(statusToSet, logoIdList);
	}
}
*/