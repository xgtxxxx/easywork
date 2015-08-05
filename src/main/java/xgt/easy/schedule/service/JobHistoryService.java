/**
 * 
 */
package xgt.easy.schedule.service;

import org.quartz.JobListener;
import org.springframework.transaction.annotation.Transactional;

import xgt.easy.common.model.Pager;
import xgt.easy.schedule.model.HistorySearchModel;
import xgt.easy.schedule.model.JobHistory;


/**
 * @author Gavin
 *
 */
@Transactional
public interface JobHistoryService extends JobListener {
	public Pager<JobHistory> search(HistorySearchModel model);
}
