/**
 **Created on 02 Apr 2014
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.validation.validators;

import java.util.Map;


public abstract class CustomValidatorSupport extends ValidatorSupport
{

	private Map<String,Object> params = null;

	/**
	 * @return the params
	 */
	public Map<String, Object> getParams()
	{
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<String, Object> params)
	{
		this.params = params;
	}
	
}
