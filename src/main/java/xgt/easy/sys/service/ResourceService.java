/**
 * 
 */
package xgt.easy.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.Role;
import xgt.easy.sys.model.RoleResource;

/**
 * @author Gavin
 *
 */
@Transactional
public interface ResourceService {
	
	public List<Resource> listByLevel(int level,List<Role> roles);

	/**
	 * @param pid
	 * @return
	 */
	public List<Resource> listByParent(int pid);

	/**
	 * @param roles
	 * @param pid
	 * @return
	 */
	public List<Resource> listResources(List<Role> roles, int pid);

	/**
	 * @param menuId
	 * @param roles
	 * @return
	 */
	public List<RoleResource> getAuthurity(int menuId, int uid);
}
