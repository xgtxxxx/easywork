/**
 * 
 */
package xgt.easy.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gavin
 *
 */
public abstract class BaseDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * gerCurrentSession 会自动关闭session，使用的是当前的session事务
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	public void releaseSession(Session session){
		session.close();
	}
	
	/**
	 * 根据 id 查询信息
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings({"unchecked" })
	public T load(Class<T> c, Serializable id) {
		Session session = getSession();
		return (T)session.get(c, id);
	}

	/**
	 * 获取所有信息
	 * @param c
	 * @return
	 */
	@SuppressWarnings({"unchecked" })
	public List<T> listAll(Class<T> c) {
		String hql = "from " + c.getName();
		Session session = getSession();
		return session.createQuery(hql).list();
	}
	
	/**
	 * 获取所有信息
	 * @param c
	 * @return
	 */
	@SuppressWarnings({"unchecked" })
	public List<T> list(String hql,int start, int rows) {
		Query query = getSession().createQuery(hql);  
        //设置每页显示多少个，设置多大结果。  
        query.setMaxResults(rows);  
        //设置起点  
        query.setFirstResult(start);  
        return query.list();  
	}

	/**
	 * 获取总数量
	 * @param c
	 * @return
	 */
	public int getTotalCount(String hql) {
		Session session = getSession();
		Long count = (Long) session.createQuery(hql).uniqueResult();
		return count==null?0:count.intValue();
	}

	/**
	 * 保存
	 * 
	 * @param bean
	 * 
	 */
	public void save(T bean) {
		Session session = getSession();
		session.save(bean);
	}

	/**
	 * 更新
	 * @param bean
	 */
	public void update(T bean) {
		Session session = getSession();
		session.update(bean);
	}

	/**
	 * 删除
	 * @param bean
	 */
	public void delete(T bean) {
		Session session = getSession();
		session.delete(bean);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listBySql(String sql,Object...params){
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		for (int i=1; i<=params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
}