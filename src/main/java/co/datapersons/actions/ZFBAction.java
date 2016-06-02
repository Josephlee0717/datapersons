package co.datapersons.actions;

import java.util.HashMap;

import co.datapersons.alipay.util.zfbRequest;
import net.sf.json.JSONObject;
import com.google.zxing.*;
import com.alipay.config.*;
import com.alipay.util.*;
import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.annotation.RequiredFieldValidator;
import co.datapersons.validation.annotation.Validations;
import co.datapersons.api.ApplicationContext;
import co.datapersons.jdbc.JdbcDatabaseService;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayUserAccountSearchRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayUserAccountSearchResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

public class ZFBAction extends BaseAction {
	public void getSign(JSONObject input,JSONObject output){
		JSONObject result = new JSONObject();
		String mobileNum = this.getInput(input, "mobileNum");
		String subject = this.getInput(input, "subject");
		String fee = this.getInput(input, "total_fee");
		
		zfbRequest zfb = new zfbRequest();
		HashMap props = zfb.getZFBParameters(subject, fee);
//		HashMap props = zfb.getParameters();
		
		result.put("mobileNum", mobileNum);
		result.put("subject", subject);	
		result.put("total_fee", fee);
		result.put("sign", props.get("sign"));
		
		result.put("paygateway", props.get("paygateway"));
		result.put("service", props.get("service"));
		result.put("sign_type", props.get("sign_type"));
		result.put("out_trade_no", props.get("out_trade_no"));
		result.put("partner", props.get("partner"));
		result.put("key", props.get("key"));
		
		result.put("show_url", props.get("show_url"));
		result.put("body", props.get("body"));
		result.put("payment_type", props.get("payment_type"));
		result.put("seller_email", props.get("seller_email"));
		result.put("notify_url", props.get("notify_url"));
		result.put("return_url", props.get("return_url"));
		result.put("paymethod", props.get("paymethod"));
		result.put("defaultbank", props.get("defaultbank"));
		
		output.put("result", result);
	}
	
	public void paying(JSONObject input,JSONObject output){
		try{
		 //ÉÌ»§¶©µ¥ºÅ£¬ÉÌ»§ÍøÕ¾¶©µ¥ÏµÍ³ÖÐÎ¨Ò»¶©µ¥ºÅ£¬±ØÌî
//        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = new String(this.getInput(input, "WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");	
        //¶©µ¥Ãû³Æ£¬±ØÌî
//        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject = new String(this.getInput(input, "WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        //¸¶¿î½ð¶î£¬±ØÌî
//        String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
        String total_fee = new String(this.getInput(input, "WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
        //ÉÌÆ·ÃèÊö£¬¿É¿Õ
//        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        String body = new String(this.getInput(input, "WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        
        Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","È·ÈÏ");
		output.put("status", "0000");
		output.put("transfer", sHtmlText);
		}catch(Exception ex)
		{
			output.put("status", "0000");
			output.put("message", ex.getMessage());
		}
	}
	
	public void tradeQuery(JSONObject input,JSONObject output){
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key");
		AlipayUserAccountSearchRequest request = new AlipayUserAccountSearchRequest();
		request.setStartTime("2016-04-01 00:00:00");
		request.setEndTime("2016-04-11 00:00:00");
		request.setType("1");
		request.setPageNo("1");
		request.setPageSize("20");
//		request.setFields("fields");
		
		try{
			AlipayUserAccountSearchResponse response = alipayClient.execute(request);
			String rtn = response.toString();
		}
		catch(Exception ex){
			
		}
	}
}
