
/**
 * 
 */
package xgt.easy.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import xgt.easy.common.dao.BaseDao;
import xgt.easy.sys.dao.ResourceDao;
import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.RoleResource;

/**
 * @author Gavin
 *
 */
@Repository
public class ResourceDaoImpl extends BaseDao<Resource> implements ResourceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleResource> getAuthurity(String string,
			Map<String, Object> params) {
		Query query = getSession().createQuery(string);  
		for(String key: params.keySet()){
			query.setParameter(key, params.get(key));
		}
        return query.list();  
	}

}
