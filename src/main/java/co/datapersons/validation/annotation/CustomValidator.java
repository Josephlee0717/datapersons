/**
 **Created on 25 Mar 2014
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.validation.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomValidator
{
  public abstract String type();

  public abstract String fieldPath();

  public abstract String message();

  public abstract String[] messageParams() default {};

  public abstract ValidationParameter[] parameters();

}