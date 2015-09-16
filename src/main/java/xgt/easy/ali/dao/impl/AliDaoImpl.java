/**
 * 
 */
package xgt.easy.ali.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import xgt.easy.ali.dao.AliDao;
import xgt.easy.common.dao.BaseDao;

/**
 * @author Gavin
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class AliDaoImpl extends BaseDao implements AliDao{

	@Override
	public <T> List<T> search(String hql, Object...params) {
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		int i = 0;
		for (Object object : params) {
			query.setParameter(i++, object);
		}
		return query.list();
	}
}
