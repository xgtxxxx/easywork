/**
 * 
 */
package xgt.easy.ali.model;

/**
 * @author Gavin
 *
 */
public class Search {
	private String startMonth;
	private String endMonth;
	private String field;
	private int count;
	private int duration;
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the startMonth
	 */
	public String getStartMonth() {
		return startMonth;
	}
	/**
	 * @param startMonth the startMonth to set
	 */
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	/**
	 * @return the endMonth
	 */
	public String getEndMonth() {
		return endMonth;
	}
	/**
	 * @param endMonth the endMonth to set
	 */
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Search [startMonth=" + startMonth + ", endMonth=" + endMonth
				+ ", field=" + field + ", count=" + count + ", duration="
				+ duration + "]";
	}
}
