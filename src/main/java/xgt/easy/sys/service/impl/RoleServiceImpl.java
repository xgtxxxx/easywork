/**
 * 
 */
package xgt.easy.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.sys.dao.RoleDao;
import xgt.easy.sys.model.Role;
import xgt.easy.sys.service.RoleService;

/**
 * @author Gavin
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> listRoles(int userId) {
		String hql = "from Role where id in(select roleId from UserRole where userId=?)";
		return roleDao.list(hql, userId);
	}

}
