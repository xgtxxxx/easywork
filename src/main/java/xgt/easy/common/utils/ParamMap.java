/**
 * 
 */
package xgt.easy.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gavin
 *
 */
public class ParamMap {
	public static Map<String,Object> createParamMap(){
		return new HashMap<String,Object>();
	}
	
	public static Map<String,Object> createParamMap(String key, Object value){
		Map<String,Object> m = createParamMap();
		put(m,key,value);
		return m;
	}
	
	public static void put(Map<String,Object> param,String key,Object value){
		param.put(key, value);
	}
}
