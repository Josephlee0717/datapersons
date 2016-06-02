package co.datapersons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cloopen.rest.sdk.CCPRestSDK;
import com.cloopen.rest.sdk.CCPRestSDK.BodyType;

import org.dom4j.Document;

public class mainservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private static String DBConnection = null;
	private static String DBUser = null;
	private static String DBPassword = null;
	private static String SMSURL = null;
	private static String SMSDevelopURL = null;
	private static String SMSPort = null;
	private static String SMSAccountID = null;
	private static String SMSAccountToken = null;
	private static String SMSAPPID = null;
	private static String SMSTemplatID = null;

	
//	private static String connStr;
	
	public mainservlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document doc = null;
		response.setContentType("text/xml");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("utf-8");
		response.setDateHeader("Expires", 0);

		String webAction = request.getParameter("webAction");
		String mobileNum = request.getParameter("mobileNum");
		String password = request.getParameter("password");
		ServletException curException = null;
		
		
		// Object obj = null;
		Object responseObj = null;

		String returnValue = "";
		try {
			if (webAction == null || "".equals(webAction.trim())) {
				return;
			}
			if("requestSMS".equalsIgnoreCase(webAction)){
				if(("".equals(mobileNum))){
					return ;
				}
				
				Map props = new HashMap();
				
				Double dVer = (Math.random() * 9000 + 1000);
				String sVerifyCode = dVer.toString();
				
				String statusCode = sendVerifySMS(SMSDevelopURL,SMSPort,SMSAccountID,SMSAccountToken,SMSAPPID,mobileNum,SMSTemplatID,sVerifyCode);
				if("000000".equals(statusCode)){
					String rtnJson = "{mobile:"+mobileNum+", statusCode:000000, verifyCode:"+sVerifyCode+"}";
					List<String> listZtree = new ArrayList<String>();
					listZtree.add(rtnJson);
					returnValue = JSONUtils.toJSONString(listZtree);
				}
			}
			if("registerMobile".equalsIgnoreCase(webAction)){
//				mobileNum password
			}
				
		} catch (Exception ex) {			
			ex.printStackTrace();
			curException = new ServletException(ex);
		}
		PrintWriter out = response.getWriter();
		out.println(returnValue);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		String s = "this is a test";
		try {
			DBConnection = getServletContext().getInitParameter("DBConnection");
			SMSDevelopURL = getServletContext().getInitParameter("SMSDevelopURL");
			SMSURL = getServletContext().getInitParameter("SMSURL");
			SMSPort = getServletContext().getInitParameter("SMSPort");
			SMSAccountID = getServletContext().getInitParameter("SMSAccountID");
			SMSAccountToken = getServletContext().getInitParameter("SMSAccountToken");
			SMSAPPID = getServletContext().getInitParameter("SMSAPPID");
			SMSTemplatID = getServletContext().getInitParameter("SMSTemplatID");			
			
		} catch (Throwable ex) {			
			ex.printStackTrace();
		}
	}
	
	public String sendVerifySMS(String url , String port,String accountID, String accountToken, String appId, String mobileNum , String templatNum,String VerifyCode){
		
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(url, port);// ³õÊ¼»¯·þÎñÆ÷µØÖ·ºÍ¶Ë¿Ú£¬¸ñÊ½ÈçÏÂ£¬·þÎñÆ÷µØÖ·²»ÐèÒªÐ´https://
		restAPI.setAccount(accountID, accountToken);// ³õÊ¼»¯Ö÷ÕÊºÅºÍÖ÷ÕÊºÅTOKEN
		restAPI.setAppId(appId);// ³õÊ¼»¯Ó¦ÓÃID
		result = restAPI.sendTemplateSMS(mobileNum,templatNum ,new String[]{mobileNum,VerifyCode});

		System.out.println("SDKTestSendTemplateSMS result=" + result);
		
		return result.get("statusCode").toString();
	}
	
	

}
