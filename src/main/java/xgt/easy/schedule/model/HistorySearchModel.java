/**
 * 
 */
package xgt.easy.schedule.model;

import xgt.easy.utils.search.Logic;
import xgt.easy.utils.search.LogicType;
import xgt.easy.utils.search.OrderBy;
import xgt.easy.utils.search.SearchFrom;
import xgt.easy.utils.search.SimpleSearchModel;

/**
 * @author Gavin
 *
 */
@SearchFrom(name="JobHistory")
public class HistorySearchModel extends SimpleSearchModel {
	@Logic(type=LogicType.LIKE_RIGHT)
	private String jobName;
	@Logic(type=LogicType.LIKE_RIGHT)
	private String triggerName;
	private Boolean success;
	@OrderBy(index=1)
	@Logic(type=LogicType.GTE,field="runTime")
	private String startRunTime;
	@Logic(type=LogicType.LTE,field="runTime")
	private String endRunTime;
	@Logic(type=LogicType.IGNORE)
	private int start;
	@Logic(type=LogicType.IGNORE)
	private int limit;
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
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return this.limit;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.limit = rows;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the startRunTime
	 */
	public String getStartRunTime() {
		return startRunTime;
	}
	/**
	 * @param startRunTime the startRunTime to set
	 */
	public void setStartRunTime(String startRunTime) {
		this.startRunTime = startRunTime;
	}
	/**
	 * @return the endRunTime
	 */
	public String getEndRunTime() {
		return endRunTime;
	}
	/**
	 * @param endRunTime the endRunTime to set
	 */
	public void setEndRunTime(String endRunTime) {
		this.endRunTime = endRunTime;
	}
}
