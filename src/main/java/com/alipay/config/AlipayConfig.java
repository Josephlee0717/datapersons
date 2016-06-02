package com.alipay.config;

/* *
 *ÀàÃû£ºAlipayConfig
 *¹¦ÄÜ£º»ù´¡ÅäÖÃÀà
 *ÏêÏ¸£ºÉèÖÃÕÊ»§ÓÐ¹ØÐÅÏ¢¼°·µ»ØÂ·¾¶
 *°æ±¾£º3.4
 *ÐÞ¸ÄÈÕÆÚ£º2016-03-08
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class AlipayConfig {
	
//¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ýÇëÔÚÕâÀïÅäÖÃÄúµÄ»ù±¾ÐÅÏ¢¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý

	// ºÏ×÷Éí·ÝÕßID£¬Ç©Ô¼ÕËºÅ£¬ÒÔ2088¿ªÍ·ÓÉ16Î»´¿Êý×Ö×é³ÉµÄ×Ö·û´®£¬²é¿´µØÖ·£ºhttps://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088021003577393";
	
	// ÊÕ¿îÖ§¸¶±¦ÕËºÅ£¬ÒÔ2088¿ªÍ·ÓÉ16Î»´¿Êý×Ö×é³ÉµÄ×Ö·û´®£¬Ò»°ãÇé¿öÏÂÊÕ¿îÕËºÅ¾ÍÊÇÇ©Ô¼ÕËºÅ
	public static String seller_id = partner;

	// MD5ÃÜÔ¿£¬°²È«¼ìÑéÂë£¬ÓÉÊý×ÖºÍ×ÖÄ¸×é³ÉµÄ32Î»×Ö·û´®£¬²é¿´µØÖ·£ºhttps://b.alipay.com/order/pidAndKey.htm
    public static String key = "l1d6p68ivl61iuq83jbkjecrubkmtg0b";

	// ·þÎñÆ÷Òì²½Í¨ÖªÒ³ÃæÂ·¾¶  Ðèhttp://¸ñÊ½µÄÍêÕûÂ·¾¶£¬²»ÄÜ¼Ó?id=123ÕâÀà×Ô¶¨Òå²ÎÊý£¬±ØÐëÍâÍø¿ÉÒÔÕý³£·ÃÎÊ
	public static String notify_url = "http://127.0.0.1:8080/datapersons/notify_url.jsp";

	// Ò³ÃæÌø×ªÍ¬²½Í¨ÖªÒ³ÃæÂ·¾¶ Ðèhttp://¸ñÊ½µÄÍêÕûÂ·¾¶£¬²»ÄÜ¼Ó?id=123ÕâÀà×Ô¶¨Òå²ÎÊý£¬±ØÐëÍâÍø¿ÉÒÔÕý³£·ÃÎÊ
	public static String return_url = "http://127.0.0.1:8080/datapersons/return_url.jsp";

	// Ç©Ãû·½Ê½
	public static String sign_type = "MD5";
	
	// µ÷ÊÔÓÃ£¬´´½¨TXTÈÕÖ¾ÎÄ¼þ¼ÐÂ·¾¶£¬¼ûAlipayCore.javaÀàÖÐµÄlogResult(String sWord)´òÓ¡·½·¨¡£
	public static String log_path = "G:\\logs";
		
	// ×Ö·û±àÂë¸ñÊ½ Ä¿Ç°Ö§³Ö gbk »ò utf-8
	public static String input_charset = "utf-8";
		
	// Ö§¸¶ÀàÐÍ £¬ÎÞÐèÐÞ¸Ä
	public static String payment_type = "1";
		
	// µ÷ÓÃµÄ½Ó¿ÚÃû£¬ÎÞÐèÐÞ¸Ä
	public static String service = "create_direct_pay_by_user";


//¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡üÇëÔÚÕâÀïÅäÖÃÄúµÄ»ù±¾ÐÅÏ¢¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü
	
//¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý ÇëÔÚÕâÀïÅäÖÃ·ÀµöÓãÐÅÏ¢£¬Èç¹ûÃ»¿ªÍ¨·ÀµöÓã¹¦ÄÜ£¬Îª¿Õ¼´¿É ¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý¡ý
	
	// ·ÀµöÓãÊ±¼ä´Á  ÈôÒªÊ¹ÓÃÇëµ÷ÓÃÀàÎÄ¼þsubmitÖÐµÄquery_timestampº¯Êý
	public static String anti_phishing_key = "";
	
	// ¿Í»§¶ËµÄIPµØÖ· ·Ç¾ÖÓòÍøµÄÍâÍøIPµØÖ·£¬Èç£º221.0.0.1
	public static String exter_invoke_ip = "";
		
//¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡üÇëÔÚÕâÀïÅäÖÃ·ÀµöÓãÐÅÏ¢£¬Èç¹ûÃ»¿ªÍ¨·ÀµöÓã¹¦ÄÜ£¬Îª¿Õ¼´¿É ¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü¡ü
	
}

