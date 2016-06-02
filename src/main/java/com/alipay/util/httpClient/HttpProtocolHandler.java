package com.alipay.util.httpClient;

import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* *
 *ÀàÃû£ºHttpProtocolHandler
 *¹¦ÄÜ£ºHttpClient·½Ê½·ÃÎÊ
 *ÏêÏ¸£º»ñÈ¡Ô¶³ÌHTTPÊý¾Ý
 *°æ±¾£º3.3
 *ÈÕÆÚ£º2012-08-17
 *ËµÃ÷£º
 *ÒÔÏÂ´úÂëÖ»ÊÇÎªÁË·½±ãÉÌ»§²âÊÔ¶øÌá¹©µÄÑùÀý´úÂë£¬ÉÌ»§¿ÉÒÔ¸ù¾Ý×Ô¼ºÍøÕ¾µÄÐèÒª£¬°´ÕÕ¼¼ÊõÎÄµµ±àÐ´,²¢·ÇÒ»¶¨ÒªÊ¹ÓÃ¸Ã´úÂë¡£
 *¸Ã´úÂë½ö¹©Ñ§Ï°ºÍÑÐ¾¿Ö§¸¶±¦½Ó¿ÚÊ¹ÓÃ£¬Ö»ÊÇÌá¹©Ò»¸ö²Î¿¼¡£
 */

public class HttpProtocolHandler {

    private static String              DEFAULT_CHARSET                     = "GBK";

    /** Á¬½Ó³¬Ê±Ê±¼ä£¬ÓÉbean factoryÉèÖÃ£¬È±Ê¡Îª8ÃëÖÓ */
    private int                        defaultConnectionTimeout            = 8000;

    /** »ØÓ¦³¬Ê±Ê±¼ä, ÓÉbean factoryÉèÖÃ£¬È±Ê¡Îª30ÃëÖÓ */
    private int                        defaultSoTimeout                    = 30000;

    /** ÏÐÖÃÁ¬½Ó³¬Ê±Ê±¼ä, ÓÉbean factoryÉèÖÃ£¬È±Ê¡Îª60ÃëÖÓ */
    private int                        defaultIdleConnTimeout              = 60000;

    private int                        defaultMaxConnPerHost               = 30;

    private int                        defaultMaxTotalConn                 = 80;

    /** Ä¬ÈÏµÈ´ýHttpConnectionManager·µ»ØÁ¬½Ó³¬Ê±£¨Ö»ÓÐÔÚ´ïµ½×î´óÁ¬½ÓÊýÊ±Æð×÷ÓÃ£©£º1Ãë*/
    private static final long          defaultHttpConnectionManagerTimeout = 3 * 1000;

    /**
     * HTTPÁ¬½Ó¹ÜÀíÆ÷£¬¸ÃÁ¬½Ó¹ÜÀíÆ÷±ØÐëÊÇÏß³Ì°²È«µÄ.
     */
    private HttpConnectionManager      connectionManager;

    private static HttpProtocolHandler httpProtocolHandler                 = new HttpProtocolHandler();

    /**
     * ¹¤³§·½·¨
     * 
     * @return
     */
    public static HttpProtocolHandler getInstance() {
        return httpProtocolHandler;
    }

    /**
     * Ë½ÓÐµÄ¹¹Ôì·½·¨
     */
    private HttpProtocolHandler() {
        // ´´½¨Ò»¸öÏß³Ì°²È«µÄHTTPÁ¬½Ó³Ø
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
        connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(defaultIdleConnTimeout);

        ict.start();
    }

    /**
     * Ö´ÐÐHttpÇëÇó
     * 
     * @param request ÇëÇóÊý¾Ý
     * @param strParaFileName ÎÄ¼þÀàÐÍµÄ²ÎÊýÃû
     * @param strFilePath ÎÄ¼þÂ·¾¶
     * @return 
     * @throws HttpException, IOException 
     */
    public HttpResponse execute(HttpRequest request, String strParaFileName, String strFilePath) throws HttpException, IOException {
        HttpClient httpclient = new HttpClient(connectionManager);

        // ÉèÖÃÁ¬½Ó³¬Ê±
        int connectionTimeout = defaultConnectionTimeout;
        if (request.getConnectionTimeout() > 0) {
            connectionTimeout = request.getConnectionTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

        // ÉèÖÃ»ØÓ¦³¬Ê±
        int soTimeout = defaultSoTimeout;
        if (request.getTimeout() > 0) {
            soTimeout = request.getTimeout();
        }
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

        // ÉèÖÃµÈ´ýConnectionManagerÊÍ·ÅconnectionµÄÊ±¼ä
        httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

        String charset = request.getCharset();
        charset = charset == null ? DEFAULT_CHARSET : charset;
        HttpMethod method = null;

        //getÄ£Ê½ÇÒ²»´øÉÏ´«ÎÄ¼þ
        if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
            method = new GetMethod(request.getUrl());
            method.getParams().setCredentialCharset(charset);

            // parseNotifyConfig»á±£Ö¤Ê¹ÓÃGET·½·¨Ê±£¬requestÒ»¶¨Ê¹ÓÃQueryString
            method.setQueryString(request.getQueryString());
        } else if(strParaFileName.equals("") && strFilePath.equals("")) {
        	//postÄ£Ê½ÇÒ²»´øÉÏ´«ÎÄ¼þ
            method = new PostMethod(request.getUrl());
            ((PostMethod) method).addParameters(request.getParameters());
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
        }
        else {
        	//postÄ£Ê½ÇÒ´øÉÏ´«ÎÄ¼þ
            method = new PostMethod(request.getUrl());
            List<Part> parts = new ArrayList<Part>();
            for (int i = 0; i < request.getParameters().length; i++) {
            	parts.add(new StringPart(request.getParameters()[i].getName(), request.getParameters()[i].getValue(), charset));
            }
            //Ôö¼ÓÎÄ¼þ²ÎÊý£¬strParaFileNameÊÇ²ÎÊýÃû£¬Ê¹ÓÃ±¾µØÎÄ¼þ
            parts.add(new FilePart(strParaFileName, new FilePartSource(new File(strFilePath))));
            
            // ÉèÖÃÇëÇóÌå
            ((PostMethod) method).setRequestEntity(new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams()));
        }

        // ÉèÖÃHttp HeaderÖÐµÄUser-AgentÊôÐÔ
        method.addRequestHeader("User-Agent", "Mozilla/4.0");
        HttpResponse response = new HttpResponse();

        try {
            httpclient.executeMethod(method);
            if (request.getResultType().equals(HttpResultType.STRING)) {
                response.setStringResult(method.getResponseBodyAsString());
            } else if (request.getResultType().equals(HttpResultType.BYTES)) {
                response.setByteResult(method.getResponseBody());
            }
            response.setResponseHeaders(method.getResponseHeaders());
        } catch (UnknownHostException ex) {

            return null;
        } catch (IOException ex) {

            return null;
        } catch (Exception ex) {

            return null;
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * ½«NameValuePairsÊý×é×ª±äÎª×Ö·û´®
     * 
     * @param nameValues
     * @return
     */
    protected String toString(NameValuePair[] nameValues) {
        if (nameValues == null || nameValues.length == 0) {
            return "null";
        }

        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < nameValues.length; i++) {
            NameValuePair nameValue = nameValues[i];

            if (i == 0) {
                buffer.append(nameValue.getName() + "=" + nameValue.getValue());
            } else {
                buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
            }
        }

        return buffer.toString();
    }
}
