package org.jainbooks.ebook.custom.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jainbooks.ebook.util.PropertiesFileReaderUtil;

public class DateFormatValidatorImpl implements
		ConstraintValidator<DateFormatValidator, String> {


	@Override
	public void initialize(DateFormatValidator constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
			
			String DATE_PATTERN = PropertiesFileReaderUtil
					.getPropertyValue("date.regx");

			Pattern pattern = Pattern.compile(DATE_PATTERN);
			Matcher matcher = pattern.matcher(value);

			if (matcher.matches()) {

				matcher.reset();

				if (matcher.find()) {

					String day = matcher.group(2);
					String month = matcher.group(1);
					int year = Integer.parseInt(matcher.group(3));

					if (day.equals("31")
							&& (month.equals("4") || month.equals("6")
									|| month.equals("9") || month.equals("11")
									|| month.equals("04") || month.equals("06") || month
										.equals("09"))) {
						return false; // only 1,3,5,7,8,10,12 has 31 days
					} else if (month.equals("2") || month.equals("02")) {
						// leap year
						if (year % 4 == 0) {
							if (day.equals("30") || day.equals("31")) {
								return false;
							} else {
								return true;
							}
						} else {
							if (day.equals("29") || day.equals("30")
									|| day.equals("31")) {
								return false;
							} else {
								return true;
							}
						}
					} else {
						return true;
					}
				} else {
					return false;
				}
			} else {
				
		        return false;
			}
		}
}
