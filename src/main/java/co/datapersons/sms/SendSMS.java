package co.datapersons.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import com.cloopen.rest.sdk.CCPRestSDK;

public class SendSMS {	
	public String requestSMSVerify(String mobileNum,String VerifyCode){
		Properties properties = new Properties();				
		InputStream in = this.getClass().getResourceAsStream("/sms.properties");
		HashMap<String, Object> result = null;
		
		try
		{
			properties.load(in);
			
			String SMSURL = properties.getProperty("SMSURL").trim();
			String SMSDevelopURL = properties.getProperty("SMSDevelopURL").trim();
			String SMSPort = properties.getProperty("SMSPort").trim();
			String SMSAccountID = properties.getProperty("SMSAccountID").trim();
			String SMSAccountToken = properties.getProperty("SMSAccountToken").trim();
			String SMSAPPID = properties.getProperty("SMSAPPID").trim();
			String SMSTemplatID = properties.getProperty("SMSTemplatID").trim();
			
			CCPRestSDK restAPI = new CCPRestSDK();
			restAPI.init(SMSDevelopURL, SMSPort);// ³õÊ¼»¯·þÎñÆ÷µØÖ·ºÍ¶Ë¿Ú£¬¸ñÊ½ÈçÏÂ£¬·þÎñÆ÷µØÖ·²»ÐèÒªÐ´https://
			restAPI.setAccount(SMSAccountID, SMSAccountToken);// ³õÊ¼»¯Ö÷ÕÊºÅºÍÖ÷ÕÊºÅTOKEN
			restAPI.setAppId(SMSAPPID);// ³õÊ¼»¯Ó¦ÓÃID
			result = restAPI.sendTemplateSMS(mobileNum,SMSTemplatID ,new String[]{mobileNum,VerifyCode});

			System.out.println("SDKTestSendTemplateSMS result=" + result);
			
			return result.get("statusCode").toString();			
		}
		catch (IOException e)
		{
			return "99999";

		}
		catch (Throwable e)
		{
			return "99999";
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
//		return "";
	}
		
}
