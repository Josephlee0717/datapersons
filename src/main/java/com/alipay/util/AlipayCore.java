package com.alipay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.PartSource;

import com.alipay.config.AlipayConfig;

/* *
 *ÀàÃû£ºAlipayFunction
 *¹¦ÄÜ£ºÖ§¸¶±¦½Ó¿Ú¹«ÓÃº¯ÊýÀà
 *ÏêÏ¸£º¸ÃÀàÊÇÇëÇó¡¢Í¨Öª·µ»ØÁ½¸öÎÄ¼þËùµ÷ÓÃµÄ¹«ÓÃº¯ÊýºËÐÄ´¦ÀíÎÄ¼þ£¬²»ÐèÒªÐÞ¸Ä
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2012-08-14
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class AlipayCore {

    /** 
     * ³ýÈ¥Êý×éÖÐµÄ¿ÕÖµºÍÇ©Ãû²ÎÊý
     * @param sArray Ç©Ãû²ÎÊý×é
     * @return È¥µô¿ÕÖµÓëÇ©Ãû²ÎÊýºóµÄÐÂÇ©Ãû²ÎÊý×é
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * °ÑÊý×éËùÓÐÔªËØÅÅÐò£¬²¢°´ÕÕ¡°²ÎÊý=²ÎÊýÖµ¡±µÄÄ£Ê½ÓÃ¡°&¡±×Ö·ûÆ´½Ó³É×Ö·û´®
     * @param params ÐèÒªÅÅÐò²¢²ÎÓë×Ö·ûÆ´½ÓµÄ²ÎÊý×é
     * @return Æ´½Óºó×Ö·û´®
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//Æ´½ÓÊ±£¬²»°üÀ¨×îºóÒ»¸ö&×Ö·û
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * Ð´ÈÕÖ¾£¬·½±ã²âÊÔ£¨¿´ÍøÕ¾ÐèÇó£¬Ò²¿ÉÒÔ¸Ä³É°Ñ¼ÇÂ¼´æÈëÊý¾Ý¿â£©
     * @param sWord ÒªÐ´ÈëÈÕÖ¾ÀïµÄÎÄ±¾ÄÚÈÝ
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** 
     * Éú³ÉÎÄ¼þÕªÒª
     * @param strFilePath ÎÄ¼þÂ·¾¶
     * @param file_digest_type ÕªÒªËã·¨
     * @return ÎÄ¼þÕªÒª½á¹û
     */
    public static String getAbstract(String strFilePath, String file_digest_type) throws IOException {
        PartSource file = new FilePartSource(new File(strFilePath));
    	if(file_digest_type.equals("MD5")){
    		return DigestUtils.md5Hex(file.createInputStream());
    	}
    	else if(file_digest_type.equals("SHA")) {
    		return DigestUtils.sha256Hex(file.createInputStream());
    	}
    	else {
    		return "";
    	}
    }
}
