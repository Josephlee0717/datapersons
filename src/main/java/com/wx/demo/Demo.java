package com.wx.demo;

import java.util.SortedMap;
import java.util.TreeMap;

import com.wx.utils.GetWxOrderno;
import com.wx.utils.RequestHandler;
import com.wx.utils.Sha1Util;
import com.wx.utils.TenpayUtil;

/**
 * @author ex_yangxiaoyi
 * 
 */
public class Demo {
	//Î¢ÐÅÖ§¸¶ÉÌ»§¿ªÍ¨ºó Î¢ÐÅ»áÌá¹©appidºÍappsecretºÍÉÌ»§ºÅpartner
	private static String appid = "";
	private static String appsecret = "";
	private static String partner = "";
	//Õâ¸ö²ÎÊýpartnerkeyÊÇÔÚÉÌ»§ºóÌ¨ÅäÖÃµÄÒ»¸ö32Î»µÄkey,Î¢ÐÅÉÌ»§Æ½Ì¨-ÕË»§ÉèÖÃ-°²È«ÉèÖÃ-api°²È«
	private static String partnerkey = "";
	//openId ÊÇÎ¢ÐÅÓÃ»§Õë¶Ô¹«ÖÚºÅµÄ±êÊ¶£¬ÊÚÈ¨µÄ²¿·ÖÕâÀï²»½âÊÍ
	private static String openId = "";
	//Î¢ÐÅÖ§¸¶³É¹¦ºóÍ¨ÖªµØÖ· ±ØÐëÒªÇó80¶Ë¿Ú²¢ÇÒµØÖ·²»ÄÜ´ø²ÎÊý
	private static String notifyurl = "";																	 // Key

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Î¢ÐÅÖ§¸¶jsApi
		WxPayDto tpWxPay = new WxPayDto();
		tpWxPay.setOpenId(openId);
		tpWxPay.setBody("ååä¿¡æ¯");
		tpWxPay.setOrderId(getNonceStr());
		tpWxPay.setSpbillCreateIp("127.0.0.1");
		tpWxPay.setTotalFee("0.01");
	    getPackage(tpWxPay);
	    
	  //É¨ÂëÖ§¸¶
	    WxPayDto tpWxPay1 = new WxPayDto();
	    tpWxPay1.setBody("ååä¿¡æ¯");
	    tpWxPay1.setOrderId(getNonceStr());
	    tpWxPay1.setSpbillCreateIp("127.0.0.1");
	    tpWxPay1.setTotalFee("0.01");
		getCodeurl(tpWxPay1);

	}
	
	/**
	 * »ñÈ¡Î¢ÐÅÉ¨ÂëÖ§¸¶¶þÎ¬ÂëÁ¬½Ó
	 */
	public static String getCodeurl(WxPayDto tpWxPayDto){
		
		// 1 ²ÎÊý
		// ¶©µ¥ºÅ
		String orderId = tpWxPayDto.getOrderId();
		// ¸½¼ÓÊý¾Ý Ô­Ñù·µ»Ø
		String attach = "";
		// ×Ü½ð¶îÒÔ·ÖÎªµ¥Î»£¬²»´øÐ¡Êýµã
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
				
		// ¶©µ¥Éú³ÉµÄ»úÆ÷ IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// ÕâÀïnotify_urlÊÇ Ö§¸¶Íê³ÉºóÎ¢ÐÅ·¢¸ø¸ÃÁ´½ÓÐÅÏ¢£¬¿ÉÒÔÅÐ¶Ï»áÔ±ÊÇ·ñÖ§¸¶³É¹¦£¬¸Ä±ä¶©µ¥×´Ì¬µÈ¡£
		String notify_url = notifyurl;
		String trade_type = "NATIVE";

		// ÉÌ»§ºÅ
		String mch_id = partner;
		// Ëæ»ú×Ö·û´®
		String nonce_str = getNonceStr();

		// ÉÌÆ·ÃèÊö¸ù¾ÝÇé¿öÐÞ¸Ä
		String body = tpWxPayDto.getBody();

		// ÉÌ»§¶©µ¥ºÅ
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// ÕâÀïÐ´µÄ½ð¶îÎª1 ·Öµ½Ê±ÐÞ¸Ä
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "</xml>";
		String code_url = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		code_url = new GetWxOrderno().getCodeUrl(createOrderURL, xml);
		System.out.println("code_url----------------"+code_url);
		
		return code_url;
	}
	
	
	/**
	 * »ñÈ¡ÇëÇóÔ¤Ö§¸¶id±¨ÎÄ
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getPackage(WxPayDto tpWxPayDto) {
		
		String openId = tpWxPayDto.getOpenId();
		// 1 ²ÎÊý
		// ¶©µ¥ºÅ
		String orderId = tpWxPayDto.getOrderId();
		// ¸½¼ÓÊý¾Ý Ô­Ñù·µ»Ø
		String attach = "";
		// ×Ü½ð¶îÒÔ·ÖÎªµ¥Î»£¬²»´øÐ¡Êýµã
		String totalFee = getMoney(tpWxPayDto.getTotalFee());
		
		// ¶©µ¥Éú³ÉµÄ»úÆ÷ IP
		String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
		// ÕâÀïnotify_urlÊÇ Ö§¸¶Íê³ÉºóÎ¢ÐÅ·¢¸ø¸ÃÁ´½ÓÐÅÏ¢£¬¿ÉÒÔÅÐ¶Ï»áÔ±ÊÇ·ñÖ§¸¶³É¹¦£¬¸Ä±ä¶©µ¥×´Ì¬µÈ¡£
		String notify_url = notifyurl;
		String trade_type = "JSAPI";

		// ---±ØÐë²ÎÊý
		// ÉÌ»§ºÅ
		String mch_id = partner;
		// Ëæ»ú×Ö·û´®
		String nonce_str = getNonceStr();

		// ÉÌÆ·ÃèÊö¸ù¾ÝÇé¿öÐÞ¸Ä
		String body = tpWxPayDto.getBody();

		// ÉÌ»§¶©µ¥ºÅ
		String out_trade_no = orderId;

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// ÕâÀïÐ´µÄ½ð¶îÎª1 ·Öµ½Ê±ÐÞ¸Ä
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + out_trade_no
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openId + "</openid>"
				+ "</xml>";
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("»ñÈ¡µ½µÄÔ¤Ö§¸¶ID£º" + prepay_id);
		
		
		//»ñÈ¡prepay_idºó£¬Æ´½Ó×îºóÇëÇóÖ§¸¶ËùÐèÒªµÄpackage
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//ÒªÇ©Ãû
		String finalsign = reqHandler.createSign(finalpackage);
		
		String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		System.out.println("V3 jsApi package:"+finaPackage);
		return finaPackage;
	}

	/**
	 * »ñÈ¡Ëæ»ú×Ö·û´®
	 * @return
	 */
	public static String getNonceStr() {
		// Ëæ»úÊý
		String currTime = TenpayUtil.getCurrTime();
		// 8Î»ÈÕÆÚ
		String strTime = currTime.substring(8, currTime.length());
		// ËÄÎ»Ëæ»úÊý
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10Î»ÐòÁÐºÅ,¿ÉÒÔ×ÔÐÐµ÷Õû¡£
		return strTime + strRandom;	}

	/**
	 * Ôª×ª»»³É·Ö
	 * @param money
	 * @return
	 */
	public static String getMoney(String amount) {
		if(amount==null){
			return "";
		}
		// ½ð¶î×ª»¯Îª·ÖÎªµ¥Î»
		String currency =  amount.replaceAll("\\$|\\ï¿¥|\\,", "");   //´¦Àí°üº¬, £¤ »òÕß$µÄ½ð¶î  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString(); 
	}

}
