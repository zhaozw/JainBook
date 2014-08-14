package org.jainbooks.ebook.service;

import java.io.IOException;

import org.jainbooks.ebook.dto.SaveUserPasscodeDto;

public interface ImageService {

	public void saveUserPasscode(SaveUserPasscodeDto saveUserPasscodeDto) throws IOException;
}
