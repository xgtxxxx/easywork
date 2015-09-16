/**
 * 
 */
package xgt.easy.schedule.job.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import xgt.easy.schedule.job.AbstractJob;

/**
 * @author Gavin
 *
 */
public class NewJob extends AbstractJob {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("==========new job==========");
	}

}
