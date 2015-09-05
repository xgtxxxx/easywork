/**
 * 
 */
package xgt.easy.sys.dao;

import java.util.List;
import java.util.Map;

import xgt.easy.sys.model.ActiveMenu;
import xgt.easy.sys.model.User;

/**
 * @author Gavin
 *
 */
public interface UserDao {
	public User getUser(String hql,Object...params);

	/**
	 * @param hql
	 * @param createParamMap
	 * @return
	 */
	public List<ActiveMenu> listActiveMenus(String hql,
			Map<String, Object> createParamMap);

}
