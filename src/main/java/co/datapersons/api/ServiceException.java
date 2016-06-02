package co.datapersons.api;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException
{

	protected String defaultKey = StringUtils.EMPTY;
	protected String errorCode = StringUtils.EMPTY;
	
	public ServiceException()
	{
		super();
	}

	public ServiceException(String key, Throwable cause)
	{
		super(key, cause);
	}

	public ServiceException(String key)
	{
		super(key);
	}

	public ServiceException(Throwable cause)
	{
		super(cause);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage()
	{
		String message = super.getMessage();
		if(StringUtils.isEmpty(message)&&StringUtils.isEmpty(defaultKey)){//unknow system exception
			message = "@SYS_ERR";
		}else if(StringUtils.isEmpty(message)&&!StringUtils.isEmpty(defaultKey)){//service exception use default message key
			message= defaultKey.toString();
		}
		
		if(message.startsWith("@")){
			
			return StringUtils.EMPTY;
		}
		
		return message;
	}
	
	
	public String getMsgKey(){
		String key = StringUtils.EMPTY;
		String message = super.getMessage();
		if(StringUtils.isEmpty(message)&&StringUtils.isEmpty(defaultKey)){//unknow system exception
			key = "@SYS_ERR";
		}else if(StringUtils.isEmpty(message)&&!StringUtils.isEmpty(defaultKey)){//service exception use default message key
			key= defaultKey.toString();
		}else {
			key = message.toString();
		}
		
		return key;
	}

	/**
	 * @return the detail
	 */
	public String getDetail()
	{
		String detail = StringUtils.EMPTY;
		if(this.getCause()!=null){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			try
			{
				this.getCause().printStackTrace(pw);
			}
			finally
			{
				pw.close();
			}
			detail = sw.toString();
		}
		return detail;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	
	
}
