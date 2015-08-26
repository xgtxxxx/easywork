/**
 * 
 */
package xgt.easy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.common.utils.ParamMap;
import xgt.easy.sys.dao.ResourceDao;
import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.Role;
import xgt.easy.sys.model.RoleResource;
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
	public List<Resource> listByLevel(int level, List<Role> roles) {
		
		StringBuffer hql = new StringBuffer("from Resource where level=?");
		
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

	@Override
	public List<Resource> listResources(List<Role> roles, int pid) {
		StringBuffer hql = new StringBuffer("from Resource where parent.id=:pid and id in (select resourceId from RoleResource where ");
		int index = 0;
		for (Role role : roles) {
			if(index>0){
				hql.append(" or ");
			}
			hql.append("roleId=").append(role.getId());
		}
		hql.append(")");
		return this.resourceDao.query(hql.toString(), ParamMap.createParamMap("pid", pid));
	}

	@Override
	public List<RoleResource> getAuthurity(int menuId, int userId) {
		StringBuffer hql = new StringBuffer("from RoleResource where resourceId=:menuId and roleId in (select roleId from UserRole where userId=:userId)");
		Map<String,Object> params = ParamMap.createParamMap("menuId", menuId);
		params.put("userId", userId);
		
		List<RoleResource> l = this.resourceDao.getAuthurity(hql.toString(), params);
		if(l==null){
			l = new ArrayList<RoleResource>();
		}
		return l;
	}

}
