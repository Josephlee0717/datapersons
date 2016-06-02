package com.alipay.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;

/* *
 *ÀàÃû£ºAlipaySubmit
 *¹¦ÄÜ£ºÖ§¸¶±¦¸÷½Ó¿ÚÇëÇóÌá½»Àà
 *ÏêÏ¸£º¹¹ÔìÖ§¸¶±¦¸÷½Ó¿Ú±íµ¥HTMLÎÄ±¾£¬»ñÈ¡Ô¶³ÌHTTPÊý¾Ý
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2012-08-13
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class AlipaySubmit {
    
    /**
     * Ö§¸¶±¦Ìá¹©¸øÉÌ»§µÄ·þÎñ½ÓÈëÍø¹ØURL(ÐÂ)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
    /**
     * Éú³ÉÇ©Ãû½á¹û
     * @param sPara ÒªÇ©ÃûµÄÊý×é
     * @return Ç©Ãû½á¹û×Ö·û´®
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = AlipayCore.createLinkString(sPara); //°ÑÊý×éËùÓÐÔªËØ£¬°´ÕÕ¡°²ÎÊý=²ÎÊýÖµ¡±µÄÄ£Ê½ÓÃ¡°&¡±×Ö·ûÆ´½Ó³É×Ö·û´®
        String mysign = "";
        if(AlipayConfig.sign_type.equals("MD5") ) {
        	mysign = MD5.sign(prestr, AlipayConfig.key, AlipayConfig.input_charset);
        }
        return mysign;
    }
	
    /**
     * Éú³ÉÒªÇëÇó¸øÖ§¸¶±¦µÄ²ÎÊýÊý×é
     * @param sParaTemp ÇëÇóÇ°µÄ²ÎÊýÊý×é
     * @return ÒªÇëÇóµÄ²ÎÊýÊý×é
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //³ýÈ¥Êý×éÖÐµÄ¿ÕÖµºÍÇ©Ãû²ÎÊý
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //Éú³ÉÇ©Ãû½á¹û
        String mysign = buildRequestMysign(sPara);

        //Ç©Ãû½á¹ûÓëÇ©Ãû·½Ê½¼ÓÈëÇëÇóÌá½»²ÎÊý×éÖÐ
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.sign_type);

        return sPara;
    }

    /**
     * ½¨Á¢ÇëÇó£¬ÒÔ±íµ¥HTMLÐÎÊ½¹¹Ôì£¨Ä¬ÈÏ£©
     * @param sParaTemp ÇëÇó²ÎÊýÊý×é
     * @param strMethod Ìá½»·½Ê½¡£Á½¸öÖµ¿ÉÑ¡£ºpost¡¢get
     * @param strButtonName È·ÈÏ°´Å¥ÏÔÊ¾ÎÄ×Ö
     * @return Ìá½»±íµ¥HTMLÎÄ±¾
     */
    public static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //´ýÇëÇó²ÎÊýÊý×é
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayConfig.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit°´Å¥¿Ø¼þÇë²»Òªº¬ÓÐnameÊôÐÔ
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
 
    
    /**
     * ÓÃÓÚ·ÀµöÓã£¬µ÷ÓÃ½Ó¿Úquery_timestampÀ´»ñÈ¡Ê±¼ä´ÁµÄ´¦Àíº¯Êý
     * ×¢Òâ£ºÔ¶³Ì½âÎöXML³ö´í£¬Óë·þÎñÆ÷ÊÇ·ñÖ§³ÖSSLµÈÅäÖÃÓÐ¹Ø
     * @return Ê±¼ä´Á×Ö·û´®
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
	public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //¹¹Ôì·ÃÎÊquery_timestamp½Ó¿ÚµÄURL´®
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner + "&_input_charset" +AlipayConfig.input_charset;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // ½ØÈ¡²¿·Ö²»ÐèÒª½âÎöµÄÐÅÏ¢
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // ÅÐ¶ÏÊÇ·ñÓÐ³É¹¦±êÊ¾
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
}
