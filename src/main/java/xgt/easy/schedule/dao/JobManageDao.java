/**
 * 
 */
package xgt.easy.schedule.dao;

import java.util.List;

/**
 * @author Gavin
 *
 */
public interface JobManageDao {
	public List<Object> list(String sql,Object...params);
}
