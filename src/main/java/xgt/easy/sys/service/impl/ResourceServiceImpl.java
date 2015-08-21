/**
 * 
 */
package xgt.easy.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.common.utils.ParamMap;
import xgt.easy.sys.dao.ResourceDao;
import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.Role;
import xgt.easy.sys.model.User;
import xgt.easy.sys.service.ResourceService;
import xgt.easy.sys.service.RoleService;

/**
 * @author Gavin
 *
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public List<Resource> listByLevel(int level, User user) {
		
		StringBuffer hql = new StringBuffer("from Resource where level=?");
		
		List<Role> roles = roleService.listRoles(user.getId());
		
		int i = 0;
		for(Role role: roles){
			if(i==0){
				hql.append(" and id in (select resourceId from RoleResource where ");
			}else{
				hql.append(" or ");
			}
			hql.append("roleId=").append(role.getId());
			i++;
		}
		hql.append(")");
		
		return resourceDao.list(hql.toString(), level);
	}

	@Override
	public List<Resource> listByParent(int pid) {
		String hql = "from Resource where parent.id=:pid";
		return this.resourceDao.query(hql,ParamMap.createParamMap("pid", pid));
	}

}
