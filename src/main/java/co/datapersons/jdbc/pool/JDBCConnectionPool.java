package co.datapersons.jdbc.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import co.datapersons.api.AbstractService;

public class JDBCConnectionPool extends AbstractService
{

	private DataSource dataSource;

	public JDBCConnectionPool(){
		super();
	}
	/*
	 * (non-Javadoc)
	 * @see co.datapersons.api.AbstractService#init()
	 */
	@Override
	public void init()
	{
		Properties properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/jdbc.properties");
		try
		{
			properties.load(in);
			String driverClassName = properties.getProperty("driverClassName").trim();
			String url = properties.getProperty("url").trim();
			String username = properties.getProperty("username").trim();
			String password = properties.getProperty("password").trim();
			int initialSize = Integer.parseInt((properties.getProperty("initialSize").trim()));
			int minIdle = Integer.parseInt((properties.getProperty("minIdle")).trim());
			int maxIdle = Integer.parseInt((properties.getProperty("maxIdle")).trim());
			int maxWait = Integer.parseInt((properties.getProperty("maxWait")).trim());
			int maxActive = Integer.parseInt((properties.getProperty("maxActive")).trim());
			BasicDataSource bds = new BasicDataSource();
			bds.setUrl(url);
			bds.setDriverClassName(driverClassName);
			bds.setUsername(username);
			bds.setPassword(password);
			bds.setInitialSize(initialSize);
			bds.setMaxActive(maxActive);
			bds.setMinIdle(minIdle);
			bds.setMaxIdle(maxIdle);
			bds.setMaxWait(maxWait);
			dataSource = bds;
		}
		catch (IOException e)
		{
			return;

		}
		catch (Throwable e)
		{
			return;
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (IOException e)
			{
			}
		}
		super.init();
	}

	@Override
	public Object doService(Object params)
	{
		if (this.isStop())
		{
			return null;
		}

		Connection conn = null;
		if (dataSource != null)
		{
			try
			{
				conn = dataSource.getConnection();
			}
			catch (SQLException e)
			{
				//do nothing
			}
		}
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * @see co.datapersons.api.AbstractService#destory()
	 */
	@Override
	public void destory()
	{
		super.destory();
	}

}
