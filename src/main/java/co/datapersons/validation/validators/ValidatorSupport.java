/**
 ** Created on 26 Mar 2014 TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package co.datapersons.validation.validators;

import java.util.ResourceBundle;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import co.datapersons.api.ApplicationContext;

public abstract class ValidatorSupport implements Validator
{
	private String message = "";
	private String type;
	private String[] messageParameters;
	private String fieldPath;

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#getMessage(java.lang.Object)
	 */
	public String getMessage()
	{
		String msg = StringUtils.EMPTY;
		
		msg = ApplicationContext.getInstance().getMsg(this.message);
		
		if (!ArrayUtils.isEmpty(this.messageParameters))
		{
			return String.format(msg, (Object[]) this.messageParameters);
		}
		return msg;
	}

	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#setMessageParameters(java.lang.String[])
	 */
	public void setMessageParameters(String[] paramArrayOfString)
	{
		if (!ArrayUtils.isEmpty(paramArrayOfString))
		{
			this.messageParameters = paramArrayOfString;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#setValidatorType(java.lang.String)
	 */
	public void setValidatorType(String paramString)
	{
		this.type = paramString;
	}

	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#getValidatorType()
	 */
	public String getValidatorType()
	{
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#setFieldPath()
	 */
	public void setFieldPath(String paramString)
	{
		this.fieldPath = paramString;
	}

	/*
	 * (non-Javadoc)
	 * @see co.module.action.validator.validators.Validator#getFiledName()
	 */
	public String getFiledName()
	{
		return this.fieldPath;
	}

	protected String getFieldValue(Object paramObject)
	{
		String value = StringUtils.EMPTY;
		if(paramObject instanceof JSONObject){
			JSONObject json = (JSONObject) paramObject;
			json = json.getJSONObject("body");
			if(json.containsKey(this.fieldPath)){
				value = json.getString(this.fieldPath);
			}
		}
		return value;
	}
}
