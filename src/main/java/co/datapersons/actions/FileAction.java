package co.datapersons.actions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator; 
import org.apache.commons.lang.StringUtils;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.api.ApplicationContext;
import co.datapersons.jdbc.JdbcDatabaseService;
public class FileAction extends BaseAction{
	private static Logger logger = Logger.getLogger(FileAction.class); 
	
	public void SaveImage(JSONObject input,JSONObject output){
		String shopid = this.getInput(input, "shopid");
		String filename = input.getString("FileName");	
		//2, create sql parameter
		String[] queryParams = new String[]{"SaveImagePath",filename,shopid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if(r!=null){						
			output.accumulate("status", "0000");				
		}	
	}
	
}
