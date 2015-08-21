/**
 * 
 */
package xgt.easy.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.sys.model.Role;

/**
 * @author Gavin
 *
 */
@Transactional
public interface RoleService {
	public List<Role> listRoles(int userId);
}
