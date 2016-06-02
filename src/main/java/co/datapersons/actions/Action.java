package co.datapersons.actions;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public interface Action
{
	void execute(JSONObject input, JSONObject output);
	
	HttpSession getSession();
	
	void setSession(HttpSession session);
}
