/**
 * 
 */
package xgt.easy.schedule.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import xgt.easy.common.adapters.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * @author Gavin
 *
 */
@Entity
@Table(name="qrtz_history")
public class JobHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String jobName;
	@Column
	private String triggerName;
	@Column
	private Date previousFireTime;
	@Column
	private Date nextFireTime;
	@Column
	@JsonSerialize(using = DateTimeFormat.class)
	private Date runTime;
	@Column
	private int refireCount;
	@Column
	private String message;
	@Column
	private boolean success;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}
	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
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
	 * @return the runTime
	 */
	public Date getRunTime() {
		return runTime;
	}
	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}
	/**
	 * @return the refireCount
	 */
	public int getRefireCount() {
		return refireCount;
	}
	/**
	 * @param refireCount the refireCount to set
	 */
	public void setRefireCount(int refireCount) {
		this.refireCount = refireCount;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
