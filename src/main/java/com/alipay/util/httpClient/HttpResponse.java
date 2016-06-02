package com.alipay.util.httpClient;

import com.alipay.config.AlipayConfig;

import org.apache.commons.httpclient.Header;
import java.io.UnsupportedEncodingException;

/* *
 *ÀàÃû£ºHttpResponse
 *¹¦ÄÜ£ºHttp·µ»Ø¶ÔÏóµÄ·â×°
 *ÏêÏ¸£º·â×°Http·µ»ØÐÅÏ¢
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2011-08-17
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class HttpResponse {

    /**
     * ·µ»ØÖÐµÄHeaderÐÅÏ¢
     */
    private Header[] responseHeaders;

    /**
     * StringÀàÐÍµÄresult
     */
    private String   stringResult;

    /**
     * btyeÀàÐÍµÄresult
     */
    private byte[]   byteResult;

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public byte[] getByteResult() {
        if (byteResult != null) {
            return byteResult;
        }
        if (stringResult != null) {
            return stringResult.getBytes();
        }
        return null;
    }

    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() throws UnsupportedEncodingException {
        if (stringResult != null) {
            return stringResult;
        }
        if (byteResult != null) {
            return new String(byteResult, AlipayConfig.input_charset);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

}
