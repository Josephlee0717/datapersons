  
package co.datapersons.alipay.util;
import java.net.*;
import java.io.*;


public class CheckURL {
	   /**
     * ¶Ô×Ö·û´®½øÐÐMD5¼ÓÃÜ
	 * @param myUrl 
     *
     * @param url
     *
     * @return »ñÈ¡urlÄÚÈÝ
     */
  public static String check(String urlvalue ) {
	 
	 
	  String inputLine="";
	  
		try{
				URL url = new URL(urlvalue);
				
				HttpURLConnection urlConnection  = (HttpURLConnection)url.openConnection();
				
				BufferedReader in  = new BufferedReader(
			            new InputStreamReader(
			            		urlConnection.getInputStream()));
			
				inputLine = in.readLine().toString();
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println(inputLine);  ÏµÍ³´òÓ¡³ö×¥È¡µÃÑéÖ¤½á¹û
			
	    return inputLine;
  }


  }