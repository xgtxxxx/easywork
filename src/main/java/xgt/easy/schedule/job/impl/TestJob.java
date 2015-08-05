/**
 * 
 */
package xgt.easy.schedule.job.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.schedule.job.CronJob;

/**
 * @author Gavin
 *
 */
@Service
public class TestJob extends CronJob {
	
	/**
	 * @param jobName
	 */
	public TestJob() {
		this.setRunTime("*/30 * * * * ?");
	}

	@Autowired
	private DataSource dataSource;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		this.print(context.getPreviousFireTime(), "上次执行时间");
		this.print(context.getFireTime(), "本次执行时间");
		this.print(new Date(), "现在");
		print();
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void print(long time,String name){
		System.out.println(name+" : "+sdf.format(new Date(time)));
	}
	
	public void print(Date time,String name){
		if(time!=null){
		 System.out.println(name+" : "+sdf.format(time));
		}else{
			System.out.println(name);
		}
			
	}
	
	public void print(){
		System.out.println("------------------------------");
	}
}
