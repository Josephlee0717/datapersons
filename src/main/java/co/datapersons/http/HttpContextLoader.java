/**
 **Created on 2015-12-23
 **
 ** TODO To change the template for this generated file go to
 ** Window - Preferences - Java - Code Style - Code Templates
 */
package co.datapersons.http;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.datapersons.api.ApplicationContext;

public class HttpContextLoader implements ServletContextListener
{

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce)
	{
		ApplicationContext.getInstance().init();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce)
	{
		ApplicationContext.getInstance().destory();
	}

}
