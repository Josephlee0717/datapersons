package co.datapersons.jdbc.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.dbutils.ResultSetHandler;

public class JSONMapping implements ResultSetHandler<JSONArray>
{
	private JSONListRowProccessor rowProccessor = new JSONListRowProccessor(); 
	
	public JSONArray handle(ResultSet rs) throws SQLException
	{
		JSONArray result = new JSONArray();
	    while (rs.next()) {
	    	JSONObject row = this.handleRow(rs);
	    	if(row != null){
	    		result.add(row);
	    	}
	    }
	    return result;
	}
	
	public JSONObject handleRow(ResultSet rs){
		JSONObject row = new JSONObject();
		Map<String, Object> rowMap = null;
		try
		{
			rowMap = this.rowProccessor.toMap(rs);
		}
		catch (SQLException e)
		{
			//do nothing
		}
		row.accumulateAll(rowMap);
		return rowMap ==null ? null: row;
	}

}
