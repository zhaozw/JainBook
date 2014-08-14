package org.jainbooks.ebook.service.impl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jainbooks.ebook.dto.SaveUserPasscodeDto;
import org.jainbooks.ebook.exception.JainBooksException;
import org.jainbooks.ebook.service.ImageService;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.jainbooks.ebook.util.JainBookUtil;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	private static final Logger logger = Logger
			.getLogger(ImageServiceImpl.class);

	@Override
	public void saveUserPasscode(SaveUserPasscodeDto saveUserPasscodeDto)
			throws IOException {

		int combineImageHeight = 0;
		int combineImageWidth = 0;

		List<String> passcode = saveUserPasscodeDto.getPasscode();
		String email = saveUserPasscodeDto.getEmail();
		String deviceId = saveUserPasscodeDto.getDeviceId();

		// Make root folder to save images if not there.
		String rootFolderPath = createRootFolder();

		// Make user pass code folder to save images if not there.
		String userPassCodeFolderPath = rootFolderPath + "/" + email + deviceId;
		File userPassCodeFolder = createUserPassCodeFolder(userPassCodeFolderPath);

		deletePassCodeImage(userPassCodeFolderPath);

		// Save all pass code images
		savePassCodeImages(passcode, userPassCodeFolderPath);

		// Find the height and width of combined image
		for (File file : userPassCodeFolder.listFiles()) {

			BufferedImage bi = ImageIO.read(file);

			combineImageWidth += bi.getWidth();

			int imageHeight = bi.getHeight();

			if (imageHeight > combineImageHeight) {
				combineImageHeight = imageHeight;
			}
		}

		// Generate the pass code image
		generatePassCodeImage(userPassCodeFolderPath, userPassCodeFolder,
				combineImageWidth, combineImageHeight);

		deletePassCodeImages(userPassCodeFolder);

	}

	private String createRootFolder() {
		String rootFolderPath = PropertiesFileReaderUtil
				.getApplicationProperty("passcode.images.location");
		File rootFolder = new File(rootFolderPath);
		rootFolder.mkdirs();
		return rootFolderPath;
	}

	private File createUserPassCodeFolder(String userPassCodeFolderPath) {
		File userPassCodeFolder = new File(userPassCodeFolderPath);
		userPassCodeFolder.mkdirs();
		return userPassCodeFolder;
	}

	private void deletePassCodeImage(String userPassCodeFolderPath) {
		File file = new File(userPassCodeFolderPath + "/passcode.png");
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Delete all images except pass code image
	 * 
	 * @param userPassCodeFolder
	 */
	private void deletePassCodeImages(File userPassCodeFolder) {
		for (File file : userPassCodeFolder.listFiles()) {
			if (file.getName().contains("image")) {
				file.delete();
			}
		}
	}

	/**
	 * Method to convert image byte code string to byte[], resize it and save
	 * the image
	 * 
	 * @param passcode
	 * @param userPassCodeFolderPath
	 * @throws IOException
	 */
	private void savePassCodeImages(List<String> passcode,
			String userPassCodeFolderPath) {

		int counter = 0;
		Base64 base64 = new Base64();

		int resizedImageWidth = Integer.valueOf(PropertiesFileReaderUtil
				.getApplicationProperty("passcode.image.width"));
		int resizedImageHeight = Integer.valueOf(PropertiesFileReaderUtil
				.getApplicationProperty("passcode.image.height"));

		Graphics2D g = null;

		for (String imageStr : passcode) {

			counter++;

			byte[] imageByteArr = base64.decode(imageStr);

			try {

				InputStream in = new ByteArrayInputStream(imageByteArr);
				BufferedImage orignalImage = ImageIO.read(in);

				BufferedImage resizedImage = new BufferedImage(
						resizedImageWidth, resizedImageWidth,
						BufferedImage.TYPE_INT_RGB);
				g = resizedImage.createGraphics();
				g.drawImage(orignalImage, 0, 0, resizedImageWidth,
						resizedImageHeight, null);

				ImageIO.write(resizedImage, "png", new File(
						userPassCodeFolderPath + "/image" + counter + ".png"));

			} catch (IOException e) {
				logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
				throw new JainBooksException();
			} finally {
				g.dispose();
			}
		}
	}

	/**
	 * Method to generate pass code image with other images
	 * 
	 * @param userPassCodeFolderPath
	 * @param userPassCodeFolder
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	private void generatePassCodeImage(String userPassCodeFolderPath,
			File userPassCodeFolder, int width, int height) {
		int x = 0, y = 0;
		BufferedImage result = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();

		try {
			
			File fileArr[] = userPassCodeFolder.listFiles();
			Arrays.sort(fileArr);

			for (File file : fileArr) {

				BufferedImage bi = ImageIO.read(file);

				g.drawImage(bi, x, y, null);

				x += bi.getWidth() + 1;
			}

			File passcodeFile = new File(userPassCodeFolderPath
					+ "/passcode.png");

			if (passcodeFile.exists()) {
				passcodeFile.delete();
			}

			ImageIO.write(result, "png", passcodeFile);

		} catch (IOException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			throw new JainBooksException();
		} finally {
			g.dispose();
		}
	}
}
