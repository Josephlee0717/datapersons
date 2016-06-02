/**
 **Created on 26 Mar 2014
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegexFieldValidator
{
  public abstract String message();

  public abstract String[] messageParams() default {};

  public abstract String fieldPath();

  public abstract String regex();

  public abstract boolean trim();

  public abstract boolean caseSensitive();

}
