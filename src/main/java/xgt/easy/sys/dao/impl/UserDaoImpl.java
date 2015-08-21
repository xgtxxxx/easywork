/**
 * 
 */
package xgt.easy.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import xgt.easy.common.dao.BaseDao;
import xgt.easy.sys.dao.UserDao;
import xgt.easy.sys.model.ActiveMenu;
import xgt.easy.sys.model.User;

/**
 * @author Gavin
 *
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@SuppressWarnings("deprecation")
	@Override
	public User getUser(String hql, Object... params) {
		List<User> users = this.list(hql, params);
		if(users!=null&&!users.isEmpty()){
			return users.get(0);
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ActiveMenu> listActiveMenus(String hql,
			Map<String, Object> params) {
		Query query = getSession().createQuery(hql);  
		for(String key: params.keySet()){
			query.setParameter(key, params.get(key));
		}
        return query.list();  
	}

}
