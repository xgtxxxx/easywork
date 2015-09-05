/**
 * 
 */
package xgt.easy.sys.dao;

import java.util.List;

import xgt.easy.sys.model.Role;

/**
 * @author Gavin
 *
 */
public interface RoleDao {
	public List<Role> list(String hql, Object...params);
}
