package org.jainbooks.ebook.custom.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidatorImpl.class)
public @interface DateValidator {

	String message() default "Date must be greater than or equal to today's date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
