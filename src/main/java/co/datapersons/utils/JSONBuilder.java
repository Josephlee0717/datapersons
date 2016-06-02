package co.datapersons.utils;

import java.text.MessageFormat;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import co.datapersons.api.ApplicationContext;

public final class JSONBuilder
{
	private static final String RESPONSE_TMPL = "{status:'success or fail',result:'',error:{message:'',detail:''}}";

	public static JSONObject buildResponse()
	{
		return JSONObject.fromObject(RESPONSE_TMPL.toString());
	}

	public static JSONObject buildError(String message, String detail)
	{
		JSONObject err = new JSONObject();
		err.put("message", message);
		err.put("detail", detail);
		return err;
	}

	public static JSONObject buildErrorByKey(String key)
	{
		if(key.startsWith("@")){
			key = key.substring(1);
		}
		String msg = ApplicationContext.getInstance().getMsg(key);
		return buildError(msg, StringUtils.EMPTY);
	}

	public static JSONObject buildErrorByKey(String key, String detail)
	{
		if(key.startsWith("@")){
			key = key.substring(1);
		}
		String msg = ApplicationContext.getInstance().getMsg(key);
		return buildError(msg,detail);
	}

	public static JSONObject buildErrorByKey(String key, Object... args)
	{
		if(key.startsWith("@")){
			key = key.substring(1);
		}
		String msg = ApplicationContext.getInstance().getMsg(key);
		msg = MessageFormat.format(msg, args);
		return buildError(msg, StringUtils.EMPTY);
	}
}
