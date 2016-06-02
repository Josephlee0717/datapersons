package co.datapersons.jdbc;

import co.datapersons.api.ServiceException;

@SuppressWarnings("serial")
public class JDBCException extends ServiceException
{

	public JDBCException(String key)
	{
		super(key);
		this.setErrorCode("9998");
	}

	public JDBCException(String key, Throwable cause)
	{
		super(key, cause);
		this.setErrorCode("9998");
	}

}
