/**
 * 
 */
package xgt.easy.schedule.dao.impl;

import org.springframework.stereotype.Repository;

import xgt.easy.common.dao.BaseDao;
import xgt.easy.common.model.Pager;
import xgt.easy.schedule.dao.JobHistoryDao;
import xgt.easy.schedule.model.JobHistory;

/**
 * @author Gavin
 *
 */
@Repository
public class JobHistoryDaoImpl extends BaseDao<JobHistory> implements JobHistoryDao {

	@Override
	public void saveHistory(JobHistory jobHistory) {
		this.save(jobHistory);
	}

	@Override
	public Pager<JobHistory> search(String hql, int start, int rows) {
		Pager<JobHistory> pager = new Pager<JobHistory>();
		pager.setResults(this.list(hql, start, rows));
		pager.setTotalRows(this.getTotalCount("select count(*) "+hql));
		pager.setRows(rows);
		pager.setStart(start);
		pager.setTotalPage(pager.getTotalRows()/rows+(pager.getTotalPage()%rows>0?1:0));
		return pager;
	}
	
}
