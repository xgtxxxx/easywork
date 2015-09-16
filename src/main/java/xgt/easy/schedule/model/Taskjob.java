/**
 * 
 */
package xgt.easy.schedule.model;

import org.quartz.JobKey;


/**
 * @author Gavin
 *
 */
public class Taskjob {
	/**
	 * @param jobkey
	 * @param clazz
	 * @param description
	 */
	public Taskjob(JobKey jobkey, String clazz, String description) {
		super();
		this.jobkey = jobkey;
		this.clazz = clazz;
		this.description = description;
	}
	public Taskjob() {
		super();
	}
	private JobKey jobkey;
	private String clazz;
	private String description;
	
	/**
	 * @return the jobkey
	 */
	public JobKey getJobkey() {
		return jobkey;
	}
	/**
	 * @param jobkey the jobkey to set
	 */
	public void setJobkey(JobKey jobkey) {
		this.jobkey = jobkey;
	}
	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
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
}
