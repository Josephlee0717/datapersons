package co.datapersons.alipay.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.cloopen.rest.sdk.CCPRestSDK;


public class zfbRequest {
	public HashMap getZFBParameters(String subject , String fee){
		Properties properties = new Properties();				
		InputStream in = this.getClass().getResourceAsStream("/zfb.properties");
		HashMap result = new HashMap();
		Date Now_Date=new Date();
		
		try
		{
			properties.load(in);
			
			String paygateway = properties.getProperty("paygateway").trim();
			String service = properties.getProperty("service").trim();
			String sign_type = properties.getProperty("sign_type").trim();
			String out_trade_no = Now_Date.toString();;
			String input_charset = properties.getProperty("input_charset").trim();
			String partner = properties.getProperty("partner").trim();
			String key = properties.getProperty("key").trim();
			String show_url = properties.getProperty("show_url").trim();
			String body = "²âÊÔÏîÄ¿£º"+out_trade_no;
			String total_fee = fee;
			String payment_type = properties.getProperty("payment_type").trim();
			String seller_email = properties.getProperty("seller_email").trim();
			
			
			String subjectContent = subject;
			if("".equals(subject)){
				subjectContent = properties.getProperty("subject").trim();
			}
			
			String notify_url = properties.getProperty("notify_url").trim();
			
			String return_url = properties.getProperty("return_url").trim();
			String paymethod = properties.getProperty("paymethod").trim();
			String defaultbank = properties.getProperty("defaultbank").trim();
			
			String ItemUrl=Payment.CreateUrl(paygateway,service,sign_type,out_trade_no,input_charset,partner,key,show_url,body,total_fee,payment_type,seller_email,subjectContent,notify_url,return_url,paymethod,defaultbank);
			result.put("paygateway", paygateway);
			result.put("service", service);
			result.put("sign_type", sign_type);
			result.put("out_trade_no", out_trade_no);
			result.put("partner", partner);
			result.put("key", key);
			result.put("body", body);
			result.put("show_url", show_url);
			result.put("sign", ItemUrl);
			result.put("payment_type", payment_type);
			result.put("seller_email", seller_email);
			result.put("notify_url", notify_url);
			result.put("return_url", return_url);
			result.put("paymethod", paymethod);
			result.put("defaultbank", defaultbank);

			System.out.println("Alipay Request String =" + ItemUrl);
			
			return result;			
		}
		catch (IOException e)
		{
			result.put("error",  "99999");
			

		}
		catch (Throwable e)
		{
			result.put("error",  "99999");
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
		return result;
	}	
}
