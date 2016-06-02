package co.datapersons.actions;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;

import co.datapersons.api.ApplicationContext;
import co.datapersons.api.ServiceException;
import co.datapersons.jdbc.JdbcDatabaseService;
import co.datapersons.utils.JSONBuilder;
import co.datapersons.validation.AnnotationValidationBuilder;
import co.datapersons.validation.ValidationException;
import co.datapersons.validation.validators.ValidatorSupport;

public class BaseAction implements Action
{

	private static final ThreadLocal<HttpSession> threadSession = new ThreadLocal<HttpSession>();	
	public void execute(JSONObject input, JSONObject output)
	{
		try
		{
			String methodName = input.getString("method");
			Method method = MethodUtils.getAccessibleMethod(this.getClass(), methodName, new Class[] {JSONObject.class, JSONObject.class });
			ValidatorSupport[] validators = AnnotationValidationBuilder.getInstance().getValidatorSupoort(method);
			if (validators != null)
			{
				for (ValidatorSupport validator : validators)
				{
					validator.validate(this, input);
				}
			}
			method.invoke(this, input, output);
			if (!output.containsKey("status"))
			{
				output.accumulate("status", "0000");
			}
		}
		catch (ValidationException ve)
		{
			// proccess validate excpetion
			output.accumulate("status", "9995");
			JSONObject error = JSONBuilder.buildError(ve.getMessage(), StringUtils.EMPTY);
			output.accumulate("error", error);
			return;
		}catch(InvocationTargetException i){
			JSONObject error = null;
			Throwable t = i.getTargetException();
			ServiceException cause = null;
			if(t instanceof ServiceException){
				cause = (ServiceException)t;
			}else {
				cause = new ServiceException(t);
				cause.setErrorCode("9999");
			}
			output.accumulate("status", cause.getErrorCode());
			error = JSONBuilder.buildErrorByKey(cause.getMsgKey(), cause.getDetail());
			output.accumulate("error", error);
		}catch (Throwable t)
		{
			JSONObject error = null;
			ServiceException cause = null;
			if(t instanceof ServiceException){
				cause = (ServiceException)t;
			}else {
				
				cause = new ServiceException(t);
				cause.setErrorCode("9999");
			}
			output.accumulate("status", cause.getErrorCode());
			error = JSONBuilder.buildErrorByKey(cause.getMsgKey(), cause.getDetail());
			output.accumulate("error", error);
		}
	}

	private String[] constructSqlParam(String sql, String[] params)
	{
		if (params == null)
		{
			params = new String[0];
		}
		String[] queryParams = new String[params.length + 1];
		queryParams[0] = sql.toString();
		if (params.length > 0)
		{
			System.arraycopy(params, 0, queryParams, 1, params.length);
		}

		return queryParams;
	}

	protected JSONArray query(String sql, String[] params)
	{
		String[] queryParams = constructSqlParam(sql, params);
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if (r != null && r instanceof JSONArray)
		{
			JSONArray result = (JSONArray) r;
			return result;
		}
		return new JSONArray();
	}

	protected boolean executeUpdate(String sql, String[] params)
	{
		String[] queryParams = constructSqlParam(sql, params);
		JdbcDatabaseService jdbcService = ApplicationContext.getInstance().getJDBCService();
		Object r = jdbcService.doService(queryParams);
		if (r != null)
		{
			int result = Integer.parseInt(r.toString());
			return result > 0;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see co.datapersons.actions.Action#getSession()
	 */
	public HttpSession getSession()
	{
		return threadSession.get();
	}

	/*
	 * (non-Javadoc)
	 * @see co.datapersons.actions.Action#setSession()
	 */
	public void setSession(HttpSession session)
	{
		threadSession.set(session);
	}

	protected String getInput(JSONObject input, String key)
	{
		JSONObject body = input.getJSONObject("body");
		String value = StringUtils.EMPTY;
		if (body.containsKey(key))
		{
			value = body.getString(key);
		}
		return value;
	}

	protected String sqlValidate(String str)
	{
		String value = str.toString();
		String badStr = "'|\\s+and\\s+|sitename|net user|xp_cmdshell|,|\\s+like'|exec|execute|\\s+insert\\s+|\\s+create\\s+|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "table_schema|union|\\s+where\\s+|\\s+select\\s+|\\s+delete\\s+|\\s+update\\s+|\\s+order\\s+|\\s+by\\s+|count|"
				+ "chr|mid|master|truncate|char|declare|\\s+or\\s+|\\s+like\\s+|=|-|!|>|<";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++)
		{
			if (StringUtils.isEmpty(badStrs[i]))
			{
				continue;
			}
			String regex = "(?i)" + badStrs[i];
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			if (matcher.find())
			{
				value = value.replaceAll(regex, "");
			}
		}
		return value;
	}

	protected final static String MD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	protected String getUTFStr(String param)
	{
		String value = StringUtils.EMPTY;
		try
		{
			value = new String(param.getBytes(), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{

		}
		return value;
	}
	
	protected HttpServletRequest getRequest(HttpServletRequest request)
	{		
		return request;
	}
}
