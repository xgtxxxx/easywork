/**
 * 
 */
package xgt.easy.schedule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import xgt.easy.schedule.job.AbstractJob;
import xgt.easy.schedule.service.ScheduleJobService;

/**
 * @author Gavin
 *
 */
@Component
public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
	}
	@Autowired
	AbstractJob job;
	
	@Autowired
	ScheduleJobService jobService;
	
	@PostConstruct
	public void addJob(){
//		this.jobService.addJob(job);
	}
}
