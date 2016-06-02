
package com.alipay.util;

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/* *
 *ÀàÃû£ºUtilDate
 *¹¦ÄÜ£º×Ô¶¨Òå¶©µ¥Àà
 *ÏêÏ¸£º¹¤¾ßÀà£¬¿ÉÒÔÓÃ×÷»ñÈ¡ÏµÍ³ÈÕÆÚ¡¢¶©µ¥±àºÅµÈ
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2012-08-17
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */
public class UtilDate {
	
    /** ÄêÔÂÈÕÊ±·ÖÃë(ÎÞÏÂ»®Ïß) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** ÍêÕûÊ±¼ä yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** ÄêÔÂÈÕ(ÎÞÏÂ»®Ïß) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    
    /**
     * ·µ»ØÏµÍ³µ±Ç°Ê±¼ä(¾«È·µ½ºÁÃë),×÷ÎªÒ»¸öÎ¨Ò»µÄ¶©µ¥±àºÅ
     * @return
     *      ÒÔyyyyMMddHHmmssÎª¸ñÊ½µÄµ±Ç°ÏµÍ³Ê±¼ä
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * »ñÈ¡ÏµÍ³µ±Ç°ÈÕÆÚ(¾«È·µ½ºÁÃë)£¬¸ñÊ½£ºyyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * »ñÈ¡ÏµÍ³µ±ÆÚÄêÔÂÈÕ(¾«È·µ½Ìì)£¬¸ñÊ½£ºyyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * ²úÉúËæ»úµÄÈýÎ»Êý
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
}
