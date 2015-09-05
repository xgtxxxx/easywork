/**
 * 
 */
package xgt.easy.schedule.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xgt.easy.schedule.job.AbstractJob;

/**
 * @author Gavin
 *
 */
public class CacheJobFactory implements JobFactory {
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheJobFactory.class);

	@Override
	public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler)
			throws SchedulerException {
		 JobDetail jobDetail = bundle.getJobDetail();
	        Class<? extends Job> jobClass = jobDetail.getJobClass();
	        try {
	            if(LOG.isDebugEnabled()) {
	            	LOG.debug(
	                    "Producing instance of Job '" + jobDetail.getKey() + 
	                    "', class=" + jobClass.getName());
	            }
	            AbstractJob job = AbstractJob.getJob(jobClass.getName());
	            return job==null?jobClass.newInstance():job;
	        } catch (Exception e) {
	            SchedulerException se = new SchedulerException(
	                    "Problem instantiating class '"
	                            + jobDetail.getJobClass().getName() + "'", e);
	            throw se;
	        }
	}

}
