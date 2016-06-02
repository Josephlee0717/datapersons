package co.datapersons.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validations {
	  public abstract CustomValidator[] customValidators() default {};

	  public abstract RequiredFieldValidator[] requiredFields()default {};

	  public abstract RegexFieldValidator[] regexFields()default {};
}
