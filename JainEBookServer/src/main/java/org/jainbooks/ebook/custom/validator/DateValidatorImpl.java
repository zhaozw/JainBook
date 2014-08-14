package org.jainbooks.ebook.custom.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.jainbooks.ebook.util.PropertiesFileReaderUtil;
import org.jainbooks.ebook.util.JainBookUtil;

public class DateValidatorImpl implements
		ConstraintValidator<DateValidator, String> {

	private static final Logger logger = Logger
			.getLogger(DateValidatorImpl.class);

	@Override
	public void initialize(DateValidator constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				PropertiesFileReaderUtil.getPropertyValue("date.format"));
		Date offerDate;
		Calendar calendar = Calendar.getInstance();

		try {
			offerDate = dateFormat.parse(value);
			calendar.setTime(offerDate);
			calendar.add(Calendar.HOUR_OF_DAY, new Integer(
					PropertiesFileReaderUtil.getPropertyValue("hours.to.add")));
			calendar.add(
					Calendar.MINUTE,
					new Integer(PropertiesFileReaderUtil
							.getPropertyValue("minutes.to.add")));
			calendar.add(
					Calendar.SECOND,
					new Integer(PropertiesFileReaderUtil
							.getPropertyValue("seconds.to.add")));

		} catch (ParseException e) {
			logger.fatal(JainBookUtil.getExceptionDescriptionString(e));
			return false;
		}

		Date actualOfferDate = calendar.getTime();

		Date currentDate = new Date();

		int result = actualOfferDate.compareTo(currentDate);

		return result > 0 ? true : false;
	}

}
