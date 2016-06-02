/**
 **Created on 27 Mar 2014
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.validation.validators;

import org.apache.commons.lang.StringUtils;

import co.datapersons.validation.ValidationException;
import co.datapersons.actions.Action;

public class RequiredFieldValidator extends ValidatorSupport
{
	
	public RequiredFieldValidator(){
		this.setValidatorType("required");
	}
	
	/* (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#validate(java.lang.Object)
	 */
	public void validate(Action targetAction,Object paramObject) throws ValidationException
	{
		    String value = getFieldValue(paramObject);

		    if (value == null||StringUtils.isEmpty(value)){
		    	throw new ValidationException(this.getMessage());
		    }
	}

}
