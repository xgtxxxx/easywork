/**
 * 
 */
package xgt.easy.schedule.service.impl;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.schedule.dao.JobManageDao;
import xgt.easy.schedule.job.AbstractJob;
import xgt.easy.schedule.job.CronJob;
import xgt.easy.schedule.job.SimpleJob;
import xgt.easy.schedule.model.TaskTrigger;
import xgt.easy.schedule.model.Taskjob;
import xgt.easy.schedule.quartz.SpringSchedulerFactory;
import xgt.easy.schedule.service.JobHistoryService;
import xgt.easy.schedule.service.ScheduleJobService;

/**
 * @author Gavin
 *
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ScheduleJobServiceImpl.class);

	private static final String JOB_GROUP_NAME = "JOB_GROUP";

	private static final String TRIGGER_GROUP_NAME = "TRIGGER_GROUP";

	@Autowired
	private SpringSchedulerFactory springSchedulerFactory;
	
	@Autowired
	private JobHistoryService jobHistoryService;
	
	@Autowired
	private JobManageDao jobManagerDao;
	
	@PostConstruct
	public void start() {
		try {
			this.getScheduler().start();
			this.registerLogListener();;
		} catch (SchedulerException e) {
			e.printStackTrace();
			LOG.error(e.toString());
		}
	}
	
	private void registerLogListener(){
		try {
			Scheduler sched = springSchedulerFactory.getScheduler();
			sched.getListenerManager().addJobListener(jobHistoryService);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} 
	}

	public void addJob(AbstractJob job) {
		if(job.getName()==null){
			job.setName(job.getClass().getName());
		}

		JobDetail jobDetail = buliderJobDetail(job);
		Trigger trigger = builderTrigger(job);
		Scheduler sched = getScheduler();
		
		try {
			if (sched.getJobDetail(jobDetail.getKey()) == null) {
				addListeners(job.getListeners(), jobDetail.getKey(), sched);
				startJob(sched, jobDetail, trigger);
			} else {
				LOG.info("There is already a job named " + job.getName()
						+ " in job store.");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			LOG.error(e.toString());
		}
	}
	
	private void startJob(Scheduler sched, JobDetail jobDetail, Trigger trigger)
			throws SchedulerException {
		sched.scheduleJob(jobDetail, trigger);
		if (!sched.isShutdown()) {
			sched.start();
		}
	}

	private Trigger builderTrigger(AbstractJob aj) {
		Trigger trigger = null;
		if (aj instanceof CronJob) {
			CronJob job = (CronJob) aj;
			CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(job
					.getRunTime());
			if (aj.isIgoreMisfire()) {
				builder.withMisfireHandlingInstructionDoNothing();
			}
			trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(job.getName() + "-trigger",
							TRIGGER_GROUP_NAME).withSchedule(builder).build();
		}
		if (aj instanceof SimpleJob) {
			SimpleJob job = (SimpleJob) aj;
			TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger()
					.withIdentity(job.getName() + "-trigger",
							TRIGGER_GROUP_NAME);

			if (job.getRunTime() == null) {
				builder.startNow();
			} else {
				builder.startAt(job.getRunTime());
			}
			SimpleScheduleBuilder ssb = simpleSchedule();
			if (aj.isIgoreMisfire()) {
				ssb.withMisfireHandlingInstructionNextWithRemainingCount();
			}
			if (job.getIntervalSeconds() > 0 && job.getLoopCount() > 1) {
				builder.withSchedule(ssb.withIntervalInSeconds(
						job.getIntervalSeconds()).withRepeatCount(
						job.getLoopCount() - 1));
			}
			trigger = builder.build();
		}
		return trigger;
	}

	private JobDetail buliderJobDetail(AbstractJob job) {
		JobDetail jobDetail = JobBuilder.newJob(job.getClass())
				.withIdentity(job.getName(), JOB_GROUP_NAME)
				.storeDurably(job.isDurable()).build();
		return jobDetail;
	}

	private void addListeners(List<JobListener> listeners, JobKey key,
			Scheduler sched) throws SchedulerException {
		if (!listeners.isEmpty()) {
			Matcher<JobKey> matcher = KeyMatcher.keyEquals(key);
			for (JobListener jobListener : listeners) {
				sched.getListenerManager().addJobListener(jobListener, matcher);
			}
		}
	}

	private Scheduler getScheduler() {
		try {
			return springSchedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
			LOG.error(e.toString());
		}
		return null;
	}

	@Override
	public List<Taskjob> listJobs() {
		Scheduler scheduler = this.getScheduler();
		List<Taskjob> jobs = new ArrayList<Taskjob>();
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher
					.jobGroupEquals(JOB_GROUP_NAME);
			Set<JobKey> keys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : keys) {
				JobDetail jd = scheduler.getJobDetail(jobKey);
				jobs.add(new Taskjob(jd.getKey(), jd.getJobClass().getName(), jd
						.getDescription()));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobs;
	}

	@Override
	public List<TaskTrigger> listTriggers(String jobName) {
		Scheduler scheduler = this.getScheduler();
		List<TaskTrigger> tts = new ArrayList<TaskTrigger>();
		try {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(new JobKey(jobName, JOB_GROUP_NAME));
			for (Trigger trigger : triggers) {
				TaskTrigger tt = TaskTrigger.createByTrigger(trigger);
				tt.setState(scheduler.getTriggerState(trigger.getKey()).name());
				tts.add(tt);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return tts;
	}

	@Override
	public boolean pauseJob(String jobName) {
		boolean success = true;
		try {
			this.getScheduler().pauseJob(new JobKey(jobName, JOB_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean resumeJob(String jobName) {
		boolean success = true;
		try {
			this.getScheduler().resumeJob(new JobKey(jobName, JOB_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean pauseTrigger(String triggerName) {
		boolean success = true;
		try {
			this.getScheduler().pauseTrigger(
					new TriggerKey(triggerName, TRIGGER_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean resumeTrigger(String triggerName) {
		boolean success = true;
		try {
			this.getScheduler().resumeTrigger(
					new TriggerKey(triggerName, TRIGGER_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public boolean removeTrigger(String triggerName) {
		boolean success = true;
		try {
			this.getScheduler().unscheduleJob(
					new TriggerKey(triggerName, TRIGGER_GROUP_NAME));
		} catch (SchedulerException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	@Override
	public void addTrigger(TaskTrigger taskTrigger) throws ParseException, SchedulerException {
		Scheduler scheduler = this.getScheduler();
		Trigger trigger = null;
		if (taskTrigger.getType().equals(TaskTrigger.CRON)) {
			CronScheduleBuilder builder = CronScheduleBuilder
					.cronSchedule(taskTrigger.getCron());
			builder.withMisfireHandlingInstructionDoNothing();
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(taskTrigger.getName(), TRIGGER_GROUP_NAME)
					.withDescription(taskTrigger.getDescription())
					.withSchedule(builder)
					.forJob(taskTrigger.getJobKey().getName(), JOB_GROUP_NAME)
					.build();
		} else {
			TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger()
					.withIdentity(taskTrigger.getName(), TRIGGER_GROUP_NAME);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			builder.startAt(sdf.parse(taskTrigger.getRunTime()));
			builder.forJob(taskTrigger.getJobKey().getName(), JOB_GROUP_NAME);
			SimpleScheduleBuilder ssb = simpleSchedule();
			ssb.withMisfireHandlingInstructionNextWithRemainingCount();
			if (taskTrigger.getRepectInterval() > 0
					&& taskTrigger.getRepectCount() > 1) {
				builder.withSchedule(ssb.withIntervalInSeconds(
						new Long(taskTrigger.getRepectInterval()).intValue())
						.withRepeatCount(taskTrigger.getRepectCount() - 1));
			}
			trigger = builder.build();
		}
		scheduler.scheduleJob(trigger);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addJob(Taskjob taskJob) throws SchedulerException, ClassNotFoundException {
		Scheduler scheduler = this.getScheduler();
		Class<?> clazz = Class.forName(taskJob.getClazz());
		JobDetail jobDetail = JobBuilder.newJob()
				.ofType((Class<AbstractJob>)clazz)
				.withIdentity(taskJob.getJobkey().getName(), JOB_GROUP_NAME)
				.withDescription(taskJob.getDescription())
				.storeDurably(true).build();
		scheduler.addJob(jobDetail, false);
	}

	@Override
	public void deleteJob(String jobName) throws SchedulerException {
		Scheduler scheduler = this.getScheduler();
		scheduler.deleteJob(new JobKey(jobName,JOB_GROUP_NAME));
	}

	@Override
	public void startJobNow(String jobName) throws SchedulerException {
		Scheduler scheduler = this.getScheduler();
		scheduler.triggerJob(new JobKey(jobName,JOB_GROUP_NAME));
	}

	@Override
	public boolean checkJobExistByClass(String clazz) {
		if(AbstractJob.getJob(clazz)!=null){
			return true;
		}
		List<Object> results = this.jobManagerDao.list("select * from qrtz_job_details where job_class_name=?",clazz);
		if(results!=null && !results.isEmpty()){
			return true;
		}
		return false;
	}

}
