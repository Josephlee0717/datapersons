package co.datapersons.jdbc;

import co.datapersons.api.ServiceException;

@SuppressWarnings("serial")
public class JDBCServiceException extends ServiceException
{

	public JDBCServiceException(String message)
	{
		super(message);
		this.setErrorCode("9997");
	}
	
}
