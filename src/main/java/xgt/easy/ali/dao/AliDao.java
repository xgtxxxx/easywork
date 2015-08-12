/**
 * 
 */
package xgt.easy.ali.dao;

import java.util.List;

import org.hibernate.Session;

import xgt.easy.ali.model.Ath4Detail;

/**
 * @author Gavin
 *
 */
public interface AliDao {
	public Session openSession();
	public void releaseSession(Session session);
	
	public List<Ath4Detail> listAll(Class<?> clazz);
	
	public <T> List<T> search(String hql,Object...params);
}
