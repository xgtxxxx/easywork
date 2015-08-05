/**
 * 
 */
package xgt.easy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin
 *
 */
public class BaseController {
	protected Map<String,Object> success(String message){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", message);
		return result;
	}
	
	protected Map<String,Object> fail(String message){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", false);
		result.put("message", message);
		return result;
	}
	
	protected Map<String,Object> result(List<?> results, int size){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("result", results);
		result.put("totalCount", size);
		return result;
	}
}
