package com.wx.utils.http;




import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpConnect { 
	 private static HttpConnect httpConnect = new HttpConnect();
	    /**
	     * ¹¤³§·½·¨
	     * 
	     * @return
	     */
	    public static HttpConnect getInstance() {
	        return httpConnect;
	    }
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
      
		// Ô¤¶¨½Ó¿ÚµÄ·µ»Ø´¦Àí£¬¶ÔÌØÊâ×Ö·û½øÐÐ¹ýÂË
        public HttpResponse doGetStr(String url) {
    		String CONTENT_CHARSET = "GBK";
    		long time1 = System.currentTimeMillis();
    		HttpClient client = new HttpClient(connectionManager);  
            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);  
            client.getHttpConnectionManager().getParams().setSoTimeout(55000);
            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET); 
            HttpMethod method = new GetMethod(url);
            HttpResponse response = new HttpResponse();
            try {
				client.executeMethod(method);
				System.out.println("µ÷½Ó¿Ú·µ»ØµÄÊ±¼ä:"+(System.currentTimeMillis()-time1));
				response.setStringResult(method.getResponseBodyAsString());
			} catch (HttpException e) {
				method.releaseConnection();
				return null;
			} catch (IOException e) {
				method.releaseConnection();
				return null;
			}finally{
				method.releaseConnection();
			}
			return response;
    }
}