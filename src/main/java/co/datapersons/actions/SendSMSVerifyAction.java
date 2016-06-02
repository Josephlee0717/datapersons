package co.datapersons.actions;

import net.sf.json.JSONObject;

public class SendSMSVerifyAction extends BaseAction{
	public void requestVerify(JSONObject input,JSONObject output){
		
		String mobileNum = this.getInput(input, "mobileNum");
		JSONObject smsResult = new JSONObject();
		
		Double dVer = (Math.random() * 9000 + 1000);
		String sVerifyCode = dVer.toString();
		sVerifyCode = sVerifyCode.substring(0, 4);
		co.datapersons.sms.SendSMS sendSMS = new co.datapersons.sms.SendSMS();
		
		String statusCode = sendSMS.requestSMSVerify(mobileNum, sVerifyCode);
		
		if("000000".equals(statusCode)){			
			smsResult.put("mobileNum", mobileNum);
			smsResult.put("VerifyCode", sVerifyCode);				
		}
		
		output.put("result", smsResult);
	}
}
