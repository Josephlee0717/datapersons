package co.datapersons.jdbc;

import co.datapersons.api.ServiceException;

@SuppressWarnings("serial")
public class NoJDBCConnectionException extends ServiceException
{

	public NoJDBCConnectionException()
	{
		super();
		this.defaultKey = "@SYS_ERR_NOCONNECTION";
		this.setErrorCode("9996");
	}

}
