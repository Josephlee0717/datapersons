package co.datapersons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.struts2.ServletActionContext;
//import com.opensymphony.xwork2.ActionContext;

public class JSONUtils {
	/**
	* 
	* @author wangwei JSON¹¤¾ßÀà
	* @param <T>
	* 
	*/

	/***
	* ½«List¶ÔÏóÐòÁÐ»¯ÎªJSONÎÄ±¾
	*/
	public static <T> String toJSONString(List<T> list)
	{
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	/***
	* ½«¶ÔÏóÐòÁÐ»¯ÎªJSONÎÄ±¾
	* @param object
	* @return
	*/
	public static String toJSONString(Object object)
	{
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}
	/***
	* ½«JSON¶ÔÏóÊý×éÐòÁÐ»¯ÎªJSONÎÄ±¾
	* @param jsonArray
	* @return
	*/
	public static String toJSONString(JSONArray jsonArray)
	{
		return jsonArray.toString();
	}
	
	public static String toJSONString(JSONObject jsonObject)
	{
		return jsonObject.toString();
	} 
	
	public static List toArrayList(Object object)
	{
		List arrayList = new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject(object);
		Iterator it = jsonArray.iterator();
		while (it.hasNext())
		{
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator keys = jsonObject.keys();
			while (keys.hasNext())
			{
				Object key = keys.next();
				Object value = jsonObject.get(key);
				arrayList.add(value);
			}
		}
		return arrayList;
	}
	/***
	* ½«¶ÔÏó×ª»»ÎªCollection¶ÔÏó
	* @param object
	* @return
	*/
	public static Collection toCollection(Object object)
	{
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toCollection(jsonArray);
	}
	/***
	* ½«¶ÔÏó×ª»»ÎªJSON¶ÔÏóÊý×é
	* @param object
	* @return
	*/
	public static JSONArray toJSONArray(Object object)
	{
		return JSONArray.fromObject(object);
	}
	/***
	* ½«¶ÔÏó×ª»»ÎªJSON¶ÔÏó
	* @param object
	* @return
	*/
	public static JSONObject toJSONObject(Object object)
	{
		return JSONObject.fromObject(object);
	}
	/***
	* ½«¶ÔÏó×ª»»ÎªHashMap
	* @param object
	* @return
	*/
	public static HashMap toHashMap(Object object)
	{
		HashMap<String, Object> data = new HashMap<String, Object>();
		JSONObject jsonObject = JSONUtils.toJSONObject(object);
		Iterator it = jsonObject.keys();
		while (it.hasNext())
		{
			String key = String.valueOf(it.next());
			Object value = jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}
	/***
	* ½«¶ÔÏó×ª»»ÎªList<Map<String,Object>>
	* @param object
	* @return
	*/
	// ·µ»Ø·ÇÊµÌåÀàÐÍ(Map<String,Object>)µÄList
	public static List<Map<String, Object>> toList(Object object)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		for (Object obj : jsonArray)
		{
			JSONObject jsonObject = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			Iterator it = jsonObject.keys();
			while (it.hasNext())
			{
				String key = (String) it.next();
				Object value = jsonObject.get(key);
				map.put((String) key, value);
			}
			list.add(map);
		}
		return list;
	}
	/***
	* ½«JSON¶ÔÏóÊý×é×ª»»Îª´«ÈëÀàÐÍµÄList
	* @param <T>
	* @param jsonArray
	* @param objectClass
	* @return
	*/
	public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass)
	{
		return JSONArray.toList(jsonArray, objectClass);
	}
	/***
	* ½«¶ÔÏó×ª»»Îª´«ÈëÀàÐÍµÄList
	* @param <T>
	* @param jsonArray
	* @param objectClass
	* @return
	*/
	public static <T> List<T> toList(Object object, Class<T> objectClass)
	{
		JSONArray jsonArray = JSONArray.fromObject(object);
		return JSONArray.toList(jsonArray, objectClass);
	}
	
	/*
	 * 
	 * //²âÊÔÀà
		String s1 = "[{id:1, pId:0, name:\"test1\" , open:true}]"; 
		String s2 = "{id:2, pId:1, name:\"test211\" , open:true}"; 
		String s3 = "{id:3, pId:2, name:\"test311\" , open:true}"; 
		String s4 = "{id:4, pId:1, name:\"test411\" , open:true}";
		List<String> listZtree = new ArrayList<String>();
		listZtree.add(s1);
		listZtree.add(s2);
		listZtree.add(s3);
		listZtree.add(s4);
		System.out.println(JSONUtils.toJSONString(listZtree));
	 * 
	 * 
	 * */
	
}