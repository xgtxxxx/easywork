/**
 * 
 */
package xgt.easy.schedule.job;

import xgt.easy.utils.check.Required;


/**
 * @author Gavin
 *
 */
public abstract class CronJob extends AbstractJob {

	@Required
	private String runTime;

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
