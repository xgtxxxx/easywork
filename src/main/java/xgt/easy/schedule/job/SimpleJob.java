/**
 * 
 */
package xgt.easy.schedule.job;

import java.util.Date;

/**
 * @author Gavin
 *
 */
public abstract class SimpleJob extends AbstractJob {
	private Date runTime;
	private int loopCount = 1;
	private int intervalSeconds = 0;
	/**
	 * @return the startRunTime
	 */
	public Date getRunTime() {
		return runTime;
	}
	/**
	 * @param startRunTime the startRunTime to set
	 */
	public void setRunTime(Date startRunTime) {
		this.runTime = startRunTime;
	}
	/**
	 * @return the loopCount
	 */
	public int getLoopCount() {
		return loopCount;
	}
	/**
	 * @param loopCount the loopCount to set
	 */
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
	/**
	 * @return the intervalSeconds
	 */
	public int getIntervalSeconds() {
		return intervalSeconds;
	}
	/**
	 * @param intervalSeconds the intervalSeconds to set
	 */
	public void setIntervalSeconds(int intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}
}
