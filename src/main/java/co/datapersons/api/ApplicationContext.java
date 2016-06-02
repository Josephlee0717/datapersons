package co.datapersons.api;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;

import co.datapersons.actions.Action;
import co.datapersons.utils.CaseInsensitiveHashMap;
import co.datapersons.jdbc.JdbcDatabaseService;

public final class ApplicationContext implements LifeCycle
{

	private final String ACTION_PKG = "co.datapersons.actions";
	
	private JdbcDatabaseService jdbcService = null;

	private CaseInsensitiveHashMap configs = new CaseInsensitiveHashMap();

	private CaseInsensitiveHashMap actions = new CaseInsensitiveHashMap();

	private ApplicationContext()
	{

	}

	public static ApplicationContext getInstance()
	{
		return ApplicationContextHolder.context;
	}

	public JdbcDatabaseService getJDBCService()
	{
		if (this.jdbcService == null)
		{
			jdbcService = new JdbcDatabaseService();
		}

		return this.jdbcService;
	}

	@SuppressWarnings("rawtypes")
	public Action getAction(String name)
	{
		if(StringUtils.isEmpty(name)){
			return null;
		}
		
		if (this.actions.containsKey(name))
		{
			return (Action)this.actions.get(name);
		}else {
			String actionName = StringUtils.capitalize(name);
			String actionClsName = this.ACTION_PKG.concat(".").concat(actionName).concat("Action");
			try
			{
				Class actionCls = ClassUtils.getClass(actionClsName);
				Action  action = (Action)actionCls.newInstance();
				this.actions.put(name, action);
				return action;
			}
			catch (ClassNotFoundException e)
			{
				return null;
			}
			catch (InstantiationException e)
			{
				return null;
			}
			catch (IllegalAccessException e)
			{
				return null;
			}
		}
	}

	public Object getAttribute(String key)
	{

		if (this.configs.containsKey(key))
		{
			return this.configs.get(key);
		}
		return null;
	}

	public void putAttribute(String key, Object value)
	{
		this.configs.put(key, value);
	}
	
	public String getMsg(String key){
		ResourceBundle msgBundle = (ResourceBundle)ApplicationContext.getInstance().getAttribute("MSGManager");
		if(key.startsWith("@")){
			key = key.substring(1);
		}
		if(msgBundle.containsKey(key)){
			return msgBundle.getString(key);
		}

		return StringUtils.EMPTY;
	}
	
	
	private static class ApplicationContextHolder
	{
		static ApplicationContext context = new ApplicationContext();
	}
	
	public void init()
	{
		jdbcService = new JdbcDatabaseService();
		ResourceBundle msgBundle = ResourceBundle.getBundle("msg", Locale.getDefault(),ApplicationContext.class.getClassLoader());
		this.putAttribute("MSGManager", msgBundle);
	}

	public void destory()
	{
		jdbcService.destory();
	}
}
