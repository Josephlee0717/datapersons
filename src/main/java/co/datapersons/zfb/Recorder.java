package co.datapersons.zfb;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.api.ApplicationContext;
import co.datapersons.jdbc.JdbcDatabaseService;

public class Recorder {
	public String saveDb(String extraCommon,String out_trade_no,String total_fee){
//		Properties properties = new Properties();				
//		InputStream in = this.getClass().getResourceAsStream("/sql.properties");
//		HashMap<String, Object> result = null;
		
		String rtn = extraCommon;
		int fistIdx = rtn.indexOf("@");
		int secIdx = rtn.indexOf("#");
			
		String customerid = rtn.substring(0,fistIdx);
		String shopid = rtn.substring(fistIdx + 1,secIdx);
		String shopname = rtn.substring(secIdx + 1,rtn.length());
			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String consumeTime = df.format(new Date()).toString();
		//Ð´Êý¾Ý¿â
		String[] queryParams = new String[]{"updateConsume",customerid,consumeTime,shopid,out_trade_no,shopname,total_fee};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		String saveRtn = "Fail";
		if(r!=null){			
				saveRtn = "Success";
		}
		System.out.println("ZFB result=" + extraCommon +";customerid="+customerid+";shopid="+shopid+";shopname="+shopname+"; trade_no="+out_trade_no+"; total_fee="+total_fee+";saveRtn="+saveRtn);
			
		return "ZFB result=" + extraCommon + " has insert into db";	
	}
	
}
