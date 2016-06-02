package com.alipay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;

/* *
 *ÀàÃû£ºAlipayNotify
 *¹¦ÄÜ£ºÖ§¸¶±¦Í¨Öª´¦ÀíÀà
 *ÏêÏ¸£º´¦ÀíÖ§¸¶±¦¸÷½Ó¿ÚÍ¨Öª·µ»Ø
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2012-08-17
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼

 *************************×¢Òâ*************************
 *µ÷ÊÔÍ¨Öª·µ»ØÊ±£¬¿É²é¿´»ò¸ÄÐ´logÈÕÖ¾µÄÐ´ÈëTXTÀïµÄÊý¾Ý£¬À´¼ì²éÍ¨Öª·µ»ØÊÇ·ñÕý³£
 */
public class AlipayNotify {

    /**
     * Ö§¸¶±¦ÏûÏ¢ÑéÖ¤µØÖ·
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     * ÑéÖ¤ÏûÏ¢ÊÇ·ñÊÇÖ§¸¶±¦·¢³öµÄºÏ·¨ÏûÏ¢
     * @param params Í¨Öª·µ»ØÀ´µÄ²ÎÊýÊý×é
     * @return ÑéÖ¤½á¹û
     */
    public static boolean verify(Map<String, String> params) {

        //ÅÐ¶ÏresponsetTxtÊÇ·ñÎªtrue£¬isSignÊÇ·ñÎªtrue
        //responsetTxtµÄ½á¹û²»ÊÇtrue£¬Óë·þÎñÆ÷ÉèÖÃÎÊÌâ¡¢ºÏ×÷Éí·ÝÕßID¡¢notify_idÒ»·ÖÖÓÊ§Ð§ÓÐ¹Ø
        //isSign²»ÊÇtrue£¬Óë°²È«Ð£ÑéÂë¡¢ÇëÇóÊ±µÄ²ÎÊý¸ñÊ½£¨Èç£º´ø×Ô¶¨Òå²ÎÊýµÈ£©¡¢±àÂë¸ñÊ½ÓÐ¹Ø
    	String responseTxt = "false";
		if(params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
	    String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    boolean isSign = getSignVeryfy(params, sign);

        //Ð´ÈÕÖ¾¼ÇÂ¼£¨ÈôÒªµ÷ÊÔ£¬ÇëÈ¡ÏûÏÂÃæÁ½ÐÐ×¢ÊÍ£©
        //String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign + "\n ·µ»Ø»ØÀ´µÄ²ÎÊý£º" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);

        if (isSign && responseTxt.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ¸ù¾Ý·´À¡»ØÀ´µÄÐÅÏ¢£¬Éú³ÉÇ©Ãû½á¹û
     * @param Params Í¨Öª·µ»ØÀ´µÄ²ÎÊýÊý×é
     * @param sign ±È¶ÔµÄÇ©Ãû½á¹û
     * @return Éú³ÉµÄÇ©Ãû½á¹û
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
    	//¹ýÂË¿ÕÖµ¡¢signÓësign_type²ÎÊý
    	Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
        //»ñÈ¡´ýÇ©Ãû×Ö·û´®
        String preSignStr = AlipayCore.createLinkString(sParaNew);
        //»ñµÃÇ©ÃûÑéÖ¤½á¹û
        boolean isSign = false;
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	isSign = MD5.verify(preSignStr, sign, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return isSign;
    }

    /**
    * »ñÈ¡Ô¶³Ì·þÎñÆ÷ATN½á¹û,ÑéÖ¤·µ»ØURL
    * @param notify_id Í¨ÖªÐ£ÑéID
    * @return ·þÎñÆ÷ATN½á¹û
    * ÑéÖ¤½á¹û¼¯£º
    * invalidÃüÁî²ÎÊý²»¶Ô ³öÏÖÕâ¸ö´íÎó£¬Çë¼ì²â·µ»Ø´¦ÀíÖÐpartnerºÍkeyÊÇ·ñÎª¿Õ 
    * true ·µ»ØÕýÈ·ÐÅÏ¢
    * false Çë¼ì²é·À»ðÇ½»òÕßÊÇ·þÎñÆ÷×èÖ¹¶Ë¿ÚÎÊÌâÒÔ¼°ÑéÖ¤Ê±¼äÊÇ·ñ³¬¹ýÒ»·ÖÖÓ
    */
    private static String verifyResponse(String notify_id) {
        //»ñÈ¡Ô¶³Ì·þÎñÆ÷ATN½á¹û£¬ÑéÖ¤ÊÇ·ñÊÇÖ§¸¶±¦·þÎñÆ÷·¢À´µÄÇëÇó

        String partner = AlipayConfig.partner;
        String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

        return checkUrl(veryfy_url);
    }

    /**
    * »ñÈ¡Ô¶³Ì·þÎñÆ÷ATN½á¹û
    * @param urlvalue Ö¸¶¨URLÂ·¾¶µØÖ·
    * @return ·þÎñÆ÷ATN½á¹û
    * ÑéÖ¤½á¹û¼¯£º
    * invalidÃüÁî²ÎÊý²»¶Ô ³öÏÖÕâ¸ö´íÎó£¬Çë¼ì²â·µ»Ø´¦ÀíÖÐpartnerºÍkeyÊÇ·ñÎª¿Õ 
    * true ·µ»ØÕýÈ·ÐÅÏ¢
    * false Çë¼ì²é·À»ðÇ½»òÕßÊÇ·þÎñÆ÷×èÖ¹¶Ë¿ÚÎÊÌâÒÔ¼°ÑéÖ¤Ê±¼äÊÇ·ñ³¬¹ýÒ»·ÖÖÓ
    */
    private static String checkUrl(String urlvalue) {
        String inputLine = "";

        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception e) {
            e.printStackTrace();
            inputLine = "";
        }

        return inputLine;
    }
}
