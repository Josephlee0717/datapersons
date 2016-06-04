package co.datapersons.zfb;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.actions.UserAction;
import co.datapersons.api.ApplicationContext;
import co.datapersons.jdbc.JdbcDatabaseService;

public class Recorder {
	private static Logger logger = Logger.getLogger(UserAction.class); 
	
	public String saveDb(String extraCommon,String out_trade_no,String total_fee){
//		Properties properties = new Properties();				
//		InputStream in = this.getClass().getResourceAsStream("/sql.properties");
//		HashMap<String, Object> result = null;
		
		String rtn = extraCommon;
		int fistIdx = rtn.indexOf("@");
		int secIdx = rtn.indexOf("#");
		try{
		String customerid = rtn.substring(0,fistIdx);
		String shopid = rtn.substring(fistIdx + 1,secIdx);
		String shopname = rtn.substring(secIdx + 1,rtn.length());
			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String consumeTime = df.format(new Date()).toString();
		//Ð´Êý¾Ý¿â
		String[] queryParams = new String[]{"updateConsume",customerid,consumeTime,shopid,out_trade_no,"支付宝",total_fee};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		String saveRtn = "Fail";
		if(r!=null){			
				saveRtn = "Success";
				//根据paynumber + balance  来确定下一次的余额和 本次产生的QueueID
				genQueueID(customerid,consumeTime,shopid,out_trade_no,shopname,total_fee);
		}
		//System.out.println("ZFB result=" + extraCommon +";customerid="+customerid+";shopid="+shopid+";shopname="+shopname+"; trade_no="+out_trade_no+"; total_fee="+total_fee+";saveRtn="+saveRtn);
		logger.info("ZFB result=" + extraCommon +";customerid="+customerid+";shopid="+shopid+";shopname="+shopname+"; trade_no="+out_trade_no+"; total_fee="+total_fee+";saveRtn="+saveRtn);
		}
		catch(Exception ex){
			logger.error("ZFB result=" + ex );
		}
		return "ZFB result=" + extraCommon + " has insert into db";	
	}
	

	public void genQueueID(String customerid,String consumeTime,String shopid,String out_trade_no,String shopname,String total_fee){
		/*
		 * Every time, the customer create a record of pay action, I get paynumber , and count of ID ,
		 * Mak up ID list use userid , serial number and date ( userid + serial number + date).   
		 * and input minimal queueID into lineup table ,input others into shalllineup table.
		*/		
		
		double paynumber =Double.valueOf(total_fee);
		String[] queryParams = new String[]{"GetUserBalance",customerid};
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		String sBalance = "";
		if(r!=null){		
			JSONArray result = (JSONArray)r;
			if(result.size()>0){
				JSONObject jsonResult = result.getJSONObject(0);
				sBalance =  jsonResult.getString("balance");	
			}
		}
		double balance = Double.valueOf(sBalance);
		double balanceSum = 0 ;
		double dCount = 0;
		if( balance == 0.00){
			 dCount = Math.floor(paynumber/100);
			 balanceSum = paynumber;
		}else{
			balanceSum = paynumber+balance;			
			dCount = Math.floor(balanceSum/100);
			balanceSum = ((paynumber%100)+balance)%100;
		}
		
		
//		GetUserBalance
		//Count of ID
		
		int s1 = (int)dCount;
		String userid = customerid;
		Date dt = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
	    String today = sdf.format(dt);
	    DecimalFormat df = new DecimalFormat("0000");
	    
	    queryParams = new String[]{"UpdateBalanceOfUserall",Double.toString(balanceSum),userid};				
		r = jdbcService.doService(queryParams);
		logger.info("UpdateBalanceOfUserall : balance=" + Double.toString(balanceSum) + " ; userid="+ userid);
//		Map rtn = new HashMap();
		for(int i = 0 ; i< s1 ; i++){
//			rtn.put("key"+String.valueOf(i+1), userid+df.format(i+1).toString()+today);
			String ID = userid+df.format(i+1).toString()+today;
			if(i == 0 ){
				String flag = "0";
				queryParams = new String[]{"InsertIDIntoLineup",ID,today,flag,userid};				
				r = jdbcService.doService(queryParams);
				logger.info("InsertIDIntoLineup : userid = " + ID + " ; date=" +today+" ; flag ="+flag+ " ; userid=" +userid);
			}else{
				String flag = "0";
				queryParams = new String[]{"InsertIDIntoShallLineup",ID,today,flag,userid};				
				r = jdbcService.doService(queryParams);
				logger.info("InsertIDIntoShallLineup : userid = " + ID + " ; date=" +today+" ; flag ="+flag+ " ; userid=" +userid);
			}
		}
		
		//input every QueueIDs into 
//		return rtn.toString();
	}
	
	
}
