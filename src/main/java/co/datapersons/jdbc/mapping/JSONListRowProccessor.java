package co.datapersons.jdbc.mapping;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;

import co.datapersons.utils.*;

public class JSONListRowProccessor extends BasicRowProcessor
{

	/* (non-Javadoc)
	 * @see org.apache.commons.dbutils.BasicRowProcessor#toMap(java.sql.ResultSet)
	 */
	@Override
	public Map<String, Object> toMap(ResultSet rs) throws SQLException
	{
		Map<String, Object> result = new CaseInsensitiveHashMap();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
        }

        return result;
	}
}
