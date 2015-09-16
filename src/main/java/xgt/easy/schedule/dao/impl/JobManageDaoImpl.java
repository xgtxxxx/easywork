/**
 * 
 */
package xgt.easy.schedule.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import xgt.easy.common.dao.BaseDao;
import xgt.easy.schedule.dao.JobManageDao;

/**
 * @author Gavin
 *
 */
@Repository
public class JobManageDaoImpl extends BaseDao<Object> implements JobManageDao {

	@Override
	public List<Object> list(String sql, Object...params) {
		return this.listBySql(sql,params);
	}

}
