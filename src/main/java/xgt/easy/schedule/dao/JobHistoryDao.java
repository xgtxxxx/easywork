/**
 * 
 */
package xgt.easy.schedule.dao;

import xgt.easy.common.model.Pager;
import xgt.easy.schedule.model.JobHistory;

/**
 * @author Gavin
 *
 */
public interface JobHistoryDao {
	
	public void saveHistory(JobHistory jobHistory);
	
	public Pager<JobHistory> search(String hql, int start, int rows);
}
