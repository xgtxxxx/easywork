/**
 * 
 */
package xgt.easy.schedule.model;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;



/**
 * @author Gavin
 *
 */
public class TaskTrigger {
	public static final String SECOND = "S";
	public static final String MINUTE = "M";
	public static final String HOUR = "H";
	public static final String DAY = "D";
	
	public static final String SIMPLE = "SimpleTrigger";
	public static final String CRON = "CronTrigger";
	
	private JobKey jobKey;
	private String name;
	private String description;
	private int priority;
	private Date startTime;
	private Date endTime;
	private Date nextFireTime;
	private Date previousFireTime;
	private int repectCount;
	private long repectInterval;
	private String runTime;
	private String cron;
	private String type;
	private String state;
	
	public static TaskTrigger createByTrigger(Trigger trigger){
		TaskTrigger tt = new TaskTrigger();
		tt.jobKey = trigger.getJobKey();
		tt.name = trigger.getKey().getName();
		tt.description = trigger.getDescription();
		tt.priority = trigger.getPriority();
		tt.startTime = trigger.getStartTime();
		tt.endTime = trigger.getEndTime();
		tt.nextFireTime = trigger.getNextFireTime();
		tt.previousFireTime = trigger.getPreviousFireTime();
		if(trigger instanceof CronTrigger){
			CronTrigger ct = (CronTrigger)trigger;
			tt.cron = ct.getCronExpression();
			tt.type = "CronTrigger";
		}
		if(trigger instanceof SimpleTrigger){
			SimpleTrigger st = (SimpleTrigger)trigger;
			tt.repectCount = st.getRepeatCount();
			tt.repectInterval = st.getRepeatInterval();
			tt.type = "SimpleTrigger";
		}
		return tt;
	}

	/**
	 * @return the jobKey
	 */
	public JobKey getJobKey() {
		return jobKey;
	}

	/**
	 * @param jobKey the jobKey to set
	 */
	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the nextFireTime
	 */
	public Date getNextFireTime() {
		return nextFireTime;
	}

	/**
	 * @param nextFireTime the nextFireTime to set
	 */
	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	/**
	 * @return the previousFireTime
	 */
	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	/**
	 * @param previousFireTime the previousFireTime to set
	 */
	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	/**
	 * @return the repectCount
	 */
	public int getRepectCount() {
		return repectCount;
	}

	/**
	 * @param repectCount the repectCount to set
	 */
	public void setRepectCount(int repectCount) {
		this.repectCount = repectCount;
	}

	/**
	 * @return the repectInterval
	 */
	public long getRepectInterval() {
		return repectInterval;
	}

	/**
	 * @param repectInterval the repectInterval to set
	 */
	public void setRepectInterval(long repectInterval) {
		this.repectInterval = repectInterval;
	}

	/**
	 * @return the cron
	 */
	public String getCron() {
		return cron;
	}

	/**
	 * @param cron the cron to set
	 */
	public void setCron(String cron) {
		this.cron = cron;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskTrigger [jobKey=" + jobKey + ", name=" + name
				+ ", description=" + description + ", priority=" + priority
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", nextFireTime=" + nextFireTime + ", previousFireTime="
				+ previousFireTime + ", repectCount=" + repectCount
				+ ", repectInterval=" + repectInterval + ", cron=" + cron
				+ ", type=" + type + ", state=" + state + "]";
	}

	/**
	 * @return the runTime
	 */
	public String getRunTime() {
		return runTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
}
