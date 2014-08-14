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
@Constraint(validatedBy = DateFormatValidatorImpl.class)
public @interface DateFormatValidator {

	String message() default "Invalid date format";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
