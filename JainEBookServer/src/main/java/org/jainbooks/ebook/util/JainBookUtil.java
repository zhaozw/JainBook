package org.jainbooks.ebook.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.enums.IMAGE_TYPE;
import org.jainbooks.ebook.exception.JainBooksException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class JainBookUtil {

	private static final Logger logger = Logger.getLogger(JainBookUtil.class);

	/*
	 * Method returns the stack trace of exception in string format. Used for
	 * logging of exception.
	 */
	public static String getExceptionDescriptionString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	public static String passwordEncoder(String password) {
		byte[] salt = { '@', '0', '#', '3', 'c', 'D', ']', 'a' };
		String key = "ezeon8547";
		int iterationCount = 10;
		String encryptString = null;

		KeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt,
				iterationCount);

		SecretKey secKey = null;
		try {
			secKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			Cipher ecipher = Cipher.getInstance(secKey.getAlgorithm());

			ecipher.init(Cipher.ENCRYPT_MODE, secKey, paramSpec);

			String charSet = "UTF-8";

			byte[] in = password.getBytes(charSet);

			byte[] out = ecipher.doFinal(in);

			encryptString = new sun.misc.BASE64Encoder().encode(out);

		} catch (InvalidKeySpecException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (NoSuchAlgorithmException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (NoSuchPaddingException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (InvalidKeyException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (InvalidAlgorithmParameterException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (IllegalBlockSizeException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (BadPaddingException e) {
			logger.fatal(getExceptionDescriptionString(e));
		} catch (UnsupportedEncodingException e) {
			logger.fatal(getExceptionDescriptionString(e));
		}
		return encryptString;
	}

	public static String uploadImageOnServer(CommonsMultipartFile imageName,
			IMAGE_TYPE imageType) throws IOException, FileNotFoundException {

		String path = null;

		switch (imageType) {
		case BRAND_LOGO:
			path = PropertiesFileReaderUtil
					.getApplicationProperty("storagePath");
			break;

		case OFFER_IMAGE:
			path = PropertiesFileReaderUtil
					.getApplicationProperty("offers.image.storage.path");
			break;

		case OFFER_COUPON:
			path = PropertiesFileReaderUtil
					.getApplicationProperty("offers.coupons.storage.path");
			break;

		default:
			logger.error("No image type found");
			throw new JainBooksException();
		}

		System.out.println("The imageLocation is " + path);

		FileOutputStream fOut = null;
		InputStream fIn = null;
		Date date = new Date();

		/*
		 * String convertedImageName = date.toString().replaceAll(":", "") +
		 * imageName.getOriginalFilename();
		 */

		String convertedImageName = (new Long(date.getTime())).toString()
				+ imageName.getOriginalFilename();

		String storagePath = path + "/" + convertedImageName;

		fIn = imageName.getInputStream();
		fOut = new FileOutputStream(storagePath);

		byte[] buf = new byte[1024];
		int len;

		while ((len = fIn.read(buf)) > 0) {
			fOut.write(buf, 0, len);
		}

		fIn.close();
		fOut.close();

		return convertedImageName;
	}

	public static String passwordDecrypter(String password) {
		byte[] salt = { '@', '0', '#', '3', 'c', 'D', ']', 'a' };
		String secretKey = "ezeon8547";
		int iterationCount = 10;
		String decryptedString = null;
		KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt,
				iterationCount);
		SecretKey key = null;
		try {
			key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			Cipher dcipher;
			dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(password);
			byte[] utf8 = dcipher.doFinal(enc);
			String charSet = "UTF-8";
			decryptedString = new String(utf8, charSet);
		} catch (InvalidKeySpecException e) {

		} catch (NoSuchAlgorithmException e) {

		} catch (NoSuchPaddingException e) {

		} catch (InvalidKeyException e) {

		} catch (InvalidAlgorithmParameterException e) {

		} catch (IOException e) {

		} catch (IllegalBlockSizeException e) {

		} catch (BadPaddingException e) {

		}
		return decryptedString;
	}

	/**
	 * Method to generate unique name for logo. API need unique name. In
	 * database we will save unique names. But when shown to admin we will show
	 * the orignal name
	 * 
	 * @param logoName
	 * @return
	 */
	public static String generateUniqueLogoName(String logoName) {
		Date date = new Date();
		return logoName + "^" + (new Long(date.getTime())).toString();
	}

	public static String getOrignalLogoName(String logoName) {
		if (logoName.contains("^")) {
			int index = logoName.indexOf('^');
			return logoName.substring(0, index);
		}

		return logoName;
	}

	/**
	 * Method will append server url to image path
	 * 
	 * @param path
	 * @return
	 */
	public static String appendServerUrlToPath(String path) {
		return PropertiesFileReaderUtil.getApplicationProperty("server.url")
				+ "/" + path;
	}

	/**
	 * Utility method to convert a String to date
	 * 
	 * @param str
	 *            Date string to convert
	 * @param formatString
	 *            format like 'MM-dd-yyyy'
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String str, String formatString) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(formatString);
			date = (Date) dateFormat.parse(str);
		} catch (ParseException e) {
			logger.fatal(getExceptionDescriptionString(e));
			throw new JainBooksException();
		}
		return date;
	}
	
	/**
	 * Method to convert Date to String
	 * 
	 * @param dateToConvert
	 *            date to convert
	 * @param formatString
	 *            format like 'MM-dd-yyyy'
	 * @return
	 */
	public static String convertDateToString(Date dateToConvert,
			String formatString) {
		String date = null;
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		date = dateFormat.format(dateToConvert);
		return date;

	}
	
	/**
	 * Convert date from one format to other format
	 * @param dateToFormat
	 * @param formatString
	 * @return
	 */
	public static Date convertDateToFormat(Date dateToFormat,
			String formatString) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(formatString);
			date = (Date) dateFormat.parse(convertDateToString(dateToFormat,
					formatString));
		} catch (ParseException e) {
			logger.fatal(getExceptionDescriptionString(e));
			throw new JainBooksException();
		}
		return date;

	}
	
	/**
	 * To add hours,minutes and seconds to a date
	 * 
	 * @param date
	 *            java.util.Date in which we have to add
	 * @param hours
	 *            No of hours to add, hours should be in 24 day format
	 * @param min
	 *            No minutes to add
	 * @param sec
	 *            No of seconds to add
	 * @return
	 */
	public static Date addHoursMinSecToDate(Date date, int hours, int min,
			int sec) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		calendar.add(Calendar.MINUTE, min);
		calendar.add(Calendar.SECOND, sec);

		return calendar.getTime();
	}
	
	/**
	 * Method will compare the Date in string format and compare it with the
	 * current date.
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isDateValid(String value) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				PropertiesFileReaderUtil.getPropertyValue("date.format"));

		int hoursToAdd = new Integer(
				PropertiesFileReaderUtil.getPropertyValue("hours.to.add"));
		int minToAdd = new Integer(
				PropertiesFileReaderUtil.getPropertyValue("minutes.to.add"));
		int secToAdd = new Integer(
				PropertiesFileReaderUtil.getPropertyValue("seconds.to.add"));

		Date offerDate;

		try {
			offerDate = dateFormat.parse(value);

		} catch (ParseException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			return false;
		}

		Date actualOfferDate = addHoursMinSecToDate(offerDate, hoursToAdd,
				minToAdd, secToAdd);

		Date currentDate = new Date();

		int result = actualOfferDate.compareTo(currentDate);

		return result > 0 ? true : false;
	}
}
