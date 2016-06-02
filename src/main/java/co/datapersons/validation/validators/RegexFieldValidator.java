/**
 ** Created on 27 Mar 2014 TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package co.datapersons.validation.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.datapersons.validation.ValidationException;
import co.datapersons.actions.Action;

public class RegexFieldValidator extends ValidatorSupport
{

	private String regex;
	private Boolean caseSensitive = Boolean.valueOf(true);
	private Boolean trim = Boolean.valueOf(true);

	public RegexFieldValidator(){
		this.setValidatorType("regex");
	}
	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#validate(java.lang.Object)
	 */
	public void validate(Action targetAction,Object paramObject) throws ValidationException
	{
	    Object value = getFieldValue(paramObject);

	    String regexToUse = getRegex();
	    
	    if ((value == null) || (regexToUse == null)) {
	      return;
	    }

	    if (!(value instanceof String)) {
	      return;
	    }

	    String str = ((String)value).trim();
	    if (str.length() == 0)
	      return;
	    
	    Pattern pattern;
	    if (getCaseSensitive())
	      pattern = Pattern.compile(regexToUse);
	    else {
	      pattern = Pattern.compile(regexToUse, 2);
	    }

	    String compare = (String)value;
	    if (getTrim()) {
	      compare = compare.trim();
	    }
	    Matcher matcher = pattern.matcher(compare);

	    if (!matcher.matches()){
		     throw new ValidationException(this.getMessage());
	    }
	}

	/**
	 * @return the regex
	 */
	public String getRegex()
	{
		return regex;
	}

	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex)
	{
		this.regex = regex;
	}

	/**
	 * @return the caseSensitive
	 */
	public Boolean getCaseSensitive()
	{
		return caseSensitive;
	}

	/**
	 * @param caseSensitive the caseSensitive to set
	 */
	public void setCaseSensitive(Boolean caseSensitive)
	{
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return the trim
	 */
	public Boolean getTrim()
	{
		return trim;
	}

	/**
	 * @param trim the trim to set
	 */
	public void setTrim(Boolean trim)
	{
		this.trim = trim;
	}
}
