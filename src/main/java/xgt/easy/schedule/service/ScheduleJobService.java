/**
 * 
 */
package xgt.easy.schedule.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.transaction.annotation.Transactional;

import xgt.easy.schedule.job.AbstractJob;
import xgt.easy.schedule.model.TaskTrigger;
import xgt.easy.schedule.model.Taskjob;

/**
 * @author Gavin
 *
 */
public interface ScheduleJobService {
	/**
	 * @param job
	 */
	public void addJob(AbstractJob job);
	
	public List<Taskjob> listJobs();

	/**
	 * @param jobName
	 * @return
	 */
	public List<TaskTrigger> listTriggers(String jobName);
	
	public boolean pauseJob(String jobName);

	/**
	 * @param jobName
	 */
	public boolean resumeJob(String jobName);

	/**
	 * @param triggerName
	 * @return
	 */
	public boolean pauseTrigger(String triggerName);

	/**
	 * @param triggerName
	 * @return
	 */
	public boolean resumeTrigger(String triggerName);

	/**
	 * @param triggerName
	 * @return
	 */
	public boolean removeTrigger(String triggerName);

	/**
	 * @param taskTrigger
	 */
	public void addTrigger(TaskTrigger taskTrigger) throws ParseException, SchedulerException;

	/**
	 * @param taskJob
	 */
	public void addJob(Taskjob taskJob) throws SchedulerException, ClassNotFoundException;

	/**
	 * @param jobName
	 */
	public void deleteJob(String jobName) throws SchedulerException;

	/**
	 * @param jobName
	 */
	public void startJobNow(String jobName) throws SchedulerException;
	
	@Transactional(readOnly=true)
	public boolean checkJobExistByClass(String clazz);

}
