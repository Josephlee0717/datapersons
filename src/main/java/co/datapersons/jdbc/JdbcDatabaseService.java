package co.datapersons.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang.StringUtils;

import co.datapersons.api.AbstractService;
import co.datapersons.jdbc.mapping.JSONMapping;
import co.datapersons.jdbc.pool.JDBCConnectionPool;

public class JdbcDatabaseService extends AbstractService
{
	private JDBCConnectionPool pool;
	private Map<String, String> sqlMap;

	public JdbcDatabaseService(){
		super();
	}
	
	public void init()
	{
		this.pool = new JDBCConnectionPool();
		try
		{
			this.sqlMap = QueryLoader.instance().load("/sql.properties");
//			this.sqlMap = QueryLoader.instance().load("/co/datapersons/jdbc/sql.properties");
		}
		catch (IOException e)
		{
			return;
		}
		super.init();

	}

	/*
	 * (non-Javadoc)
	 * @see co.hospital.liweiqi.api.Service#doService(java.lang.Object)
	 */
	public Object doService(Object params)
	{
		if (this.isStop())
		{
			throw new JDBCServiceException("@SYS_ERR_JDBCSERVICE");
		}

		if (params instanceof String[])
		{
			if (this.pool.isStop())
			{
				throw new JDBCServiceException("@SYS_ERR_JDBCCONSERVICE");
			}

			QueryRunner qr = new QueryRunner();
			String[] sqlParams = (String[]) params;
			String sql = this.sqlMap.get(sqlParams[0]);
			if (StringUtils.isEmpty(sql))
			{
				throw new JDBCException("@SYS_ERR_NOSQL");
			}

			Connection con = (Connection) this.pool.doService(null);
			if (con == null)
			{
				throw new NoJDBCConnectionException();
			}
			Object[] queryParams = null;
			if(sqlParams.length>1){
				queryParams =Arrays.copyOfRange(sqlParams, 1, sqlParams.length);
			}else {
				queryParams = new Object[]{};
			}

			if (sql.toUpperCase().startsWith("SELECT"))
			{
				ResultSetHandler<JSONArray> resultSetHandler = new JSONMapping();
				JSONArray result = null;
				try
				{

					result = qr.query(con, sql, resultSetHandler, queryParams);
				}
				catch (SQLException e)
				{
					throw new JDBCException("@SYS_ERR_EXECUTESQL",e);
				}
				return result;
			}
			else
			{
				int result = -100;
				try
				{
					result = qr.update(con, sql, queryParams);
				}
				catch (SQLException e)
				{
					throw new JDBCException("@SYS_ERR_EXECUTESQL",e);
				}
				return result;
			}

		}
		else
		{
			throw new JDBCException("@SYS_ERR_INVAILDPARAM");
		}
	}

	public void destory()
	{
		this.pool.destory();
	}
}
