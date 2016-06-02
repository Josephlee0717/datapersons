/**
 ** Created on 2015-12-23 TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package co.datapersons.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import co.datapersons.utils.JSONBuilder;
import co.datapersons.actions.Action;
import co.datapersons.api.ApplicationContext;

import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  

/**
 * @date 2015-12-23
 * @see
 */
public class HttpServiceServlet extends HttpServlet
{

	private static final long serialVersionUID = -6786281886022213433L;
	protected String configPath = "attached/";	  
    protected String dirTemp = "attached/temp/";      
    protected String dirName = "file";  
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");		
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		JSONObject responseObj = null;
		try
		{
			JSONObject requestData = getReqeusetDataFromParameters(request);
			if (requestData == null)
			{
				JSONObject output = new JSONObject();
				output.accumulate("status", "9999");
				JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR_NOREQUEST");
				output.accumulate("error", error);
				writeOutput(response, output);
				return;
			}
			responseObj = execute(request,requestData);
		}
		catch (Throwable e)
		{
			JSONObject output = new JSONObject();
			output.accumulate("status", "9999");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			try
			{
				e.printStackTrace(pw);
			}
			finally
			{
				pw.close();
			}
			JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR", sw.toString());
			output.accumulate("error", error);
			writeOutput(response, output);
		}

		writeOutput(response, responseObj);
	}

	private JSONObject getReqeusetDataFromParameters(HttpServletRequest request) throws IOException, ServletException
	{
		JSONObject sdo = null;
		String requestData = request.getParameter("request");
		if (StringUtils.isNotEmpty(requestData))
		{
			try
			{
				sdo = JSONObject.fromObject(requestData);
			}
			catch (Throwable t)
			{
				// do nothing
			}
		}
		return sdo;
	}

	protected JSONObject execute(HttpServletRequest request,JSONObject requestData)
	{
		String[] actions = requestData.getString("action").split("\\.");
		String actionName = actions[0];
		String method = actions[1];
		requestData.put("method", method);
		Action action = ApplicationContext.getInstance().getAction(actionName);
		JSONObject output = new JSONObject();
		
		if (action == null)
		{
			output.accumulate("status", "9999");
			JSONObject error = JSONBuilder.buildErrorByKey("SYS_ERR_NOACTION", new Object[]{requestData.getString("action")});
			output.accumulate("error", error);
		}else
		{
			if(actionName.equals("file")){
				try{   
					String s = SaveFile(request , requestData);
					action.setSession(request.getSession());
					requestData.put("FileName", s);
					action.execute(requestData, output);
				}catch(Exception ex){
					
				}
				
			}			
			action.setSession(request.getSession());
			action.execute(requestData, output);
		}
		return output;
	}
	
	private String SaveFile(HttpServletRequest request, Object obj) throws IOException{
		String savePath = this.getServletContext().getRealPath("/") + configPath; 
		String tempPath = this.getServletContext().getRealPath("/") + dirTemp;  
		String newFileName = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");  
        String ymd = sdf.format(new Date());  
        savePath += "/" + ymd + "/";  
        File dirFile = new File(savePath);  
        if (!dirFile.exists()) {  
            dirFile.mkdirs();  
        }  
          
        tempPath += "/" + ymd + "/";  
        //´´½¨ÁÙÊ±ÎÄ¼þ¼Ð  
        File dirTempFile = new File(tempPath);  
        if (!dirTempFile.exists()) {  
            dirTempFile.mkdirs();  
        } 
        DiskFileItemFactory  factory = new DiskFileItemFactory();  
        factory.setSizeThreshold(20 * 1024 * 1024); 
        factory.setRepository(new File(tempPath));
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("UTF-8");  
        try {  
            List items = upload.parseRequest(request);  
            Iterator itr = items.iterator();  
              
            String name = "";  
            String qq = "";
            while (itr.hasNext()) {  
                FileItem item = (FileItem) itr.next();  
                String fileName = item.getName();  
                long fileSize = item.getSize();  
                if (!item.isFormField()) {  
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
                      
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
                    newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;  
                    
                    try{  
                        File uploadedFile = new File(savePath, newFileName);
                        OutputStream os = new FileOutputStream(uploadedFile);  
                        InputStream is = item.getInputStream();  
                        byte buf[] = new byte[1024];//¿ÉÒÔÐÞ¸Ä 1024 ÒÔÌá¸ß¶ÁÈ¡ËÙ¶È  
                        int length = 0;    
                        while( (length = is.read(buf)) > 0 ){    
                            os.write(buf, 0, length);    
                        }
                    }catch(Exception e){  
                        e.printStackTrace();  
                    }  
                }        
            }
            return "../"+configPath + ymd + "/"+newFileName;
        } catch (FileUploadException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            return "";
        }  
            		
	}
	

	private void writeOutput(HttpServletResponse response, Object obj) throws IOException
	{
		if (obj == null)
			return;

		String str = null;
		if (obj instanceof String)
		{
			str = (String) obj;
		}
		else
		{
			str = obj.toString();
		}
		response.getWriter().print(str);
	}

}
