package com.alipay.util.httpClient;

import org.apache.commons.httpclient.NameValuePair;

/* *
 *ÀàÃû£ºHttpRequest
 *¹¦ÄÜ£ºHttpÇëÇó¶ÔÏóµÄ·â×°
 *ÏêÏ¸£º·â×°HttpÇëÇó
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2011-08-17
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class HttpRequest {

    /** HTTP GET method */
    public static final String METHOD_GET        = "GET";

    /** HTTP POST method */
    public static final String METHOD_POST       = "POST";

    /**
     * ´ýÇëÇóµÄurl
     */
    private String             url               = null;

    /**
     * Ä¬ÈÏµÄÇëÇó·½Ê½
     */
    private String             method            = METHOD_POST;

    private int                timeout           = 0;

    private int                connectionTimeout = 0;

    /**
     * Post·½Ê½ÇëÇóÊ±×é×°ºÃµÄ²ÎÊýÖµ¶Ô
     */
    private NameValuePair[]    parameters        = null;

    /**
     * Get·½Ê½ÇëÇóÊ±¶ÔÓ¦µÄ²ÎÊý
     */
    private String             queryString       = null;

    /**
     * Ä¬ÈÏµÄÇëÇó±àÂë·½Ê½
     */
    private String             charset           = "GBK";

    /**
     * ÇëÇó·¢Æð·½µÄipµØÖ·
     */
    private String             clientIp;

    /**
     * ÇëÇó·µ»ØµÄ·½Ê½
     */
    private HttpResultType     resultType        = HttpResultType.BYTES;

    public HttpRequest(HttpResultType resultType) {
        super();
        this.resultType = resultType;
    }

    /**
     * @return Returns the clientIp.
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp The clientIp to set.
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public NameValuePair[] getParameters() {
        return parameters;
    }

    public void setParameters(NameValuePair[] parameters) {
        this.parameters = parameters;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return Returns the charset.
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset The charset to set.
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public HttpResultType getResultType() {
        return resultType;
    }

    public void setResultType(HttpResultType resultType) {
        this.resultType = resultType;
    }

}
