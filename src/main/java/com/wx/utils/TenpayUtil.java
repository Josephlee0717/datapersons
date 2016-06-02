package com.wx.utils;





import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TenpayUtil {
	
	private static Object Server;
	private static String QRfromGoogle;

	/**
	 * °Ñ¶ÔÏó×ª»»³É×Ö·û´®
	 * @param obj
	 * @return String ×ª»»³É×Ö·û´®,Èô¶ÔÏóÎªnull,Ôò·µ»Ø¿Õ×Ö·û´®.
	 */
	public static String toString(Object obj) {
		if(obj == null)
			return "";
		
		return obj.toString();
	}
	
	/**
	 * °Ñ¶ÔÏó×ª»»ÎªintÊýÖµ.
	 * 
	 * @param obj
	 *            °üº¬Êý×ÖµÄ¶ÔÏó.
	 * @return int ×ª»»ºóµÄÊýÖµ,¶Ô²»ÄÜ×ª»»µÄ¶ÔÏó·µ»Ø0¡£
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}
	
	/**
	 * »ñÈ¡µ±Ç°Ê±¼ä yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * »ñÈ¡µ±Ç°ÈÕÆÚ yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	/**
	 * È¡³öÒ»¸öÖ¸¶¨³¤¶È´óÐ¡µÄËæ»úÕýÕûÊý.
	 * 
	 * @param length
	 *            int Éè¶¨ËùÈ¡³öËæ»úÊýµÄ³¤¶È¡£lengthÐ¡ÓÚ11
	 * @return int ·µ»ØÉú³ÉµÄËæ»úÊý¡£
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}
	
	/**
	 * »ñÈ¡±àÂë×Ö·û¼¯
	 * @param request
	 * @param response
	 * @return String
	 */

	public static String getCharacterEncoding(HttpServletRequest request,
			HttpServletResponse response) {
		
		if(null == request || null == response) {
			return "gbk";
		}
		
		String enc = request.getCharacterEncoding();
		if(null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}
		
		if(null == enc || "".equals(enc)) {
			enc = "gbk";
		}
		
		return enc;
	}
	
	public  static String URLencode(String content){
		
		String URLencode;
		
		URLencode= replace(Server.equals(content), "+", "%20");
		
		return URLencode;
	}
	private static String replace(boolean equals, String string, String string2) {
		
		return null;
	}

	/**
	 * »ñÈ¡unixÊ±¼ä£¬´Ó1970-01-01 00:00:00¿ªÊ¼µÄÃëÊý
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		
		return date.getTime()/1000;
	}
	
	 public static String QRfromGoogle(String chl)
	    {
	        int widhtHeight = 300;
	        String EC_level = "L";
	        int margin = 0;
	        String QRfromGoogle;
	        chl = URLencode(chl);
	        
	        QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight + "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;
	       
	        return QRfromGoogle;
	    }

		/**
		 * Ê±¼ä×ª»»³É×Ö·û´®
		 * @param date Ê±¼ä
		 * @param formatType ¸ñÊ½»¯ÀàÐÍ
		 * @return String
		 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}
	
}
	
	










