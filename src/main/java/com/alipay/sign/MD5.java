package com.alipay.sign;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import org.apache.commons.codec.digest.DigestUtils;

/** 
* ¹¦ÄÜ£ºÖ§¸¶±¦MD5Ç©Ãû´¦ÀíºËÐÄÎÄ¼þ£¬²»ÐèÒªÐÞ¸Ä
* °æ±¾£º3.3
* ÐÞ¸ÄÈÕÆÚ£º2012-08-17
* ËµÃ÷£º
* ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
* ¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö
* */

public class MD5 {

    /**
     * Ç©Ãû×Ö·û´®
     * @param text ÐèÒªÇ©ÃûµÄ×Ö·û´®
     * @param key ÃÜÔ¿
     * @param input_charset ±àÂë¸ñÊ½
     * @return Ç©Ãû½á¹û
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * Ç©Ãû×Ö·û´®
     * @param text ÐèÒªÇ©ÃûµÄ×Ö·û´®
     * @param sign Ç©Ãû½á¹û
     * @param key ÃÜÔ¿
     * @param input_charset ±àÂë¸ñÊ½
     * @return Ç©Ãû½á¹û
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + key;
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5Ç©Ãû¹ý³ÌÖÐ³öÏÖ´íÎó,Ö¸¶¨µÄ±àÂë¼¯²»¶Ô,ÄúÄ¿Ç°Ö¸¶¨µÄ±àÂë¼¯ÊÇ:" + charset);
        }
    }

}