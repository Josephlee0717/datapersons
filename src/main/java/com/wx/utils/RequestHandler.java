package com.wx.utils;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/*
 'Î¢ÐÅÖ§¸¶·þÎñÆ÷Ç©ÃûÖ§¸¶ÇëÇóÇëÇóÀà
 '============================================================================
 'apiËµÃ÷£º
 'init(app_id, app_secret, partner_key, app_key);
 '³õÊ¼»¯º¯Êý£¬Ä¬ÈÏ¸øÒ»Ð©²ÎÊý¸³Öµ£¬Èçcmdno,dateµÈ¡£
 'setKey(key_)'ÉèÖÃÉÌ»§ÃÜÔ¿
 'getLasterrCode(),»ñÈ¡×îºó´íÎóºÅ
 'GetToken();»ñÈ¡Token
 'getTokenReal();Token¹ýÆÚºóÊµÊ±»ñÈ¡Token
 'createMd5Sign(signParams);Éú³ÉMd5Ç©Ãû
 'genPackage(packageParams);»ñÈ¡package°ü
 'createSHA1Sign(signParams);´´½¨Ç©ÃûSHA1
 'sendPrepay(packageParams);Ìá½»Ô¤Ö§¸¶
 'getDebugInfo(),»ñÈ¡debugÐÅÏ¢
 '============================================================================
 '*/
public class RequestHandler {
	/** Token»ñÈ¡Íø¹ØµØÖ·µØÖ· */
	private String tokenUrl;
	/** Ô¤Ö§¸¶Íø¹ØurlµØÖ· */
	private String gateUrl;
	/** ²éÑ¯Ö§¸¶Í¨ÖªÍø¹ØURL */
	private String notifyUrl;
	/** ÉÌ»§²ÎÊý */
	private String appid;
	private String appkey;
	private String partnerkey;
	private String appsecret;
	private String key;
	/** ÇëÇóµÄ²ÎÊý */
	private SortedMap parameters;
	/** Token */
	private String Token;
	private String charset;
	/** debugÐÅÏ¢ */
	private String debugInfo;
	private String last_errcode;

	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * ³õÊ¼¹¹Ôìº¯Êý¡£
	 * 
	 * @return
	 */
	public RequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		this.last_errcode = "0";
		this.request = request;
		this.response = response;
		//this.charset = "GBK";
		this.charset = "UTF-8";
		this.parameters = new TreeMap();
		// ÑéÖ¤notifyÖ§¸¶¶©µ¥Íø¹Ø
		notifyUrl = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
		
	}

	/**
	 * ³õÊ¼»¯º¯Êý¡£
	 */
	public void init(String app_id, String app_secret,	String partner_key) {
		this.last_errcode = "0";
		this.Token = "token_";
		this.debugInfo = "";
		this.appid = app_id;
		this.partnerkey = partner_key;
		this.appsecret = app_secret;
		this.key = partner_key;
	}

	public void init() {
	}

	/**
	 * »ñÈ¡×îºó´íÎóºÅ
	 */
	public String getLasterrCode() {
		return last_errcode;
	}

	/**
	 *»ñÈ¡Èë¿ÚµØÖ·,²»°üº¬²ÎÊýÖµ
	 */
	public String getGateUrl() {
		return gateUrl;
	}

	/**
	 * »ñÈ¡²ÎÊýÖµ
	 * 
	 * @param parameter
	 *            ²ÎÊýÃû³Æ
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	
	//ÉèÖÃÃÜÔ¿
	
	public void setKey(String key) {
		this.partnerkey = key;
	}
	//ÉèÖÃÎ¢ÐÅÃÜÔ¿
	public void  setAppKey(String key){
		this.appkey = key;
	}
	
	// ÌØÊâ×Ö·û´¦Àí
	public String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, this.charset).replace("+", "%20");
	}

	// »ñÈ¡packageµÄÇ©Ãû°ü
	public String genPackage(SortedMap<String, String> packageParams)
			throws UnsupportedEncodingException {
		String sign = createSign(packageParams);

		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + UrlEncode(v) + "&");
		}

		// È¥µô×îºóÒ»¸ö&
		String packageValue = sb.append("sign=" + sign).toString();
//		System.out.println("UrlEncodeå?packageValue=" + packageValue);
		return packageValue;
	}

	/**
	 * ´´½¨md5ÕªÒª,¹æÔòÊÇ:°´²ÎÊýÃû³Æa-zÅÅÐò,Óöµ½¿ÕÖµµÄ²ÎÊý²»²Î¼ÓÇ©Ãû¡£
	 */
	public String createSign(SortedMap<String, String> packageParams) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.getKey());
		String sign = MD5Util.MD5Encode(sb.toString(), this.charset)
				.toUpperCase();
		return sign;

	}
	/**
	 * ´´½¨packageÇ©Ãû
	 */
	public boolean createMd5Sign(String signParams) {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		// Ëã³öÕªÒª
		String enc = TenpayUtil.getCharacterEncoding(this.request,
				this.response);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		String tenpaySign = this.getParameter("sign").toLowerCase();

		// debugÐÅÏ¢
		this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:"
				+ tenpaySign);

		return tenpaySign.equals(sign);
	}

	

	//Êä³öXML
	   public String parseXML() {
		   StringBuffer sb = new StringBuffer();
	       sb.append("<xml>");
	       Set es = this.parameters.entrySet();
	       Iterator it = es.iterator();
	       while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				String v = (String)entry.getValue();
				if(null != v && !"".equals(v) && !"appkey".equals(k)) {
					
					sb.append("<" + k +">" + getParameter(k) + "</" + k + ">\n");
				}
			}
	       sb.append("</xml>");
			return sb.toString();
		}

		/**
		 * ÉèÖÃdebugÐÅÏ¢
		 */
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public void setPartnerkey(String partnerkey) {
		this.partnerkey = partnerkey;
	}
	public String getDebugInfo() {
		return debugInfo;
	}
	public String getKey() {
		return key;
	}

}