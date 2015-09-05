/**
 * 
 */
package xgt.easy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Gavin
 *
 */
public class JsonUtil {
	private JsonUtil(){};
	
	private static Gson gson;
	
	static{
		gson = new GsonBuilder()
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				  .disableHtmlEscaping()
				  .create();  
	}
	
	public static String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	public static <T> T fromJson(String json,Class<T> t){
		return gson.fromJson(json, t);
	}
}
