/**
 * 
 */
package xgt.easy.sys.dao;

import java.util.List;
import java.util.Map;

import xgt.easy.sys.model.Resource;

/**
 * @author Gavin
 *
 */
public interface ResourceDao {
	public List<Resource> list(String hql,Object...params);

	/**
	 * @param hql
	 * @param createParamMap
	 * @return
	 */
	public List<Resource> query(String hql,Map<String, Object> params);
}
