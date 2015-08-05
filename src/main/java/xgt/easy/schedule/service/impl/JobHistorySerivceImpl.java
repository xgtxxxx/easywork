/**
 * 
 */
package xgt.easy.schedule.service.impl;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xgt.easy.common.model.Pager;
import xgt.easy.schedule.dao.JobHistoryDao;
import xgt.easy.schedule.model.HistorySearchModel;
import xgt.easy.schedule.model.JobHistory;
import xgt.easy.schedule.service.JobHistoryService;

/**
 * @author Gavin
 *
 */
@Service
public class JobHistorySerivceImpl extends JobListenerSupport implements JobHistoryService {
	
	@Autowired
	private JobHistoryDao jobHistoryDao;
	
	@Override
	public String getName() {
		return "JobHistoryListener";
	}

	@Override
	@Transactional
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		Trigger trigger = context.getTrigger();
		String message = null;
		boolean success = true;
        if (jobException != null) {
        	message = jobException.getMessage();
        	success = false;
        } else {
        	message = String.valueOf(context.getResult());
        }
        JobHistory jh = new JobHistory();
        jh.setMessage(message);
        jh.setJobName(context.getJobDetail().getKey().getName());
        jh.setNextFireTime(trigger.getNextFireTime());
        jh.setPreviousFireTime(trigger.getPreviousFireTime());
        jh.setRefireCount(context.getRefireCount());
        jh.setRunTime(new Date());
        jh.setSuccess(success);
        jh.setTriggerName(trigger.getKey().getName());
        this.jobHistoryDao.saveHistory(jh);
	}

	@Override
	public Pager<JobHistory> search(HistorySearchModel model) {
		String hql = model.getQueryString();
		return this.jobHistoryDao.search(hql, model.getStart(), model.getRows());
	}
}
