/**
 **Created on 26 Mar 2014
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.validation.validators;

import co.datapersons.validation.ValidationException;
import co.datapersons.actions.Action;

public interface Validator
{
	  public abstract String getMessage();

	  public abstract void setMessageParameters(String[] paramArrayOfString);

	  public abstract void validate(Action targetAction,Object paramObject)  throws ValidationException;

	  public abstract void setValidatorType(String paramString);

	  public abstract String getValidatorType();
	  
	  public abstract void setFieldPath(String paramString);
	  
	  public abstract String getFiledName();
}
