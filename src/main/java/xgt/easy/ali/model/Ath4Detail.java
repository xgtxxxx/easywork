/**
 * 
 */
package xgt.easy.ali.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gavin
 *
 */
@Entity
@Table(name="ali_ath4_detail")
public class Ath4Detail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private Date insertTime;
	@Column
	private int businessMonth;
	@Column
	private Date businessDate;
	@Column
	private String skillGroup;
	@Column
	private String family;
	@Column
	private String name;
	@Column
	private String subject1;
	@Column
	private String subject2;
	@Column
	private String subject3;
	@Column
	private String subject4;
	@Column
	private int count;
	@Column
	private double duration;
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
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	/**
	 * @return the businessDate
	 */
	public Date getBusinessDate() {
		return businessDate;
	}
	/**
	 * @param businessDate the businessDate to set
	 */
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	/**
	 * @return the skillGroup
	 */
	public String getSkillGroup() {
		return skillGroup;
	}
	/**
	 * @param skillGroup the skillGroup to set
	 */
	public void setSkillGroup(String skillGroup) {
		this.skillGroup = skillGroup;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
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
	 * @return the subject1
	 */
	public String getSubject1() {
		return subject1;
	}
	/**
	 * @param subject1 the subject1 to set
	 */
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	/**
	 * @return the subject2
	 */
	public String getSubject2() {
		return subject2;
	}
	/**
	 * @param subject2 the subject2 to set
	 */
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	/**
	 * @return the subject3
	 */
	public String getSubject3() {
		return subject3;
	}
	/**
	 * @param subject3 the subject3 to set
	 */
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}
	/**
	 * @return the subject4
	 */
	public String getSubject4() {
		return subject4;
	}
	/**
	 * @param subject4 the subject4 to set
	 */
	public void setSubject4(String subject4) {
		this.subject4 = subject4;
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
	public double getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * @return the businessMonth
	 */
	public int getBusinessMonth() {
		return businessMonth;
	}
	/**
	 * @param businessMonth the businessMonth to set
	 */
	public void setBusinessMonth(int businessMonth) {
		this.businessMonth = businessMonth;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ath4Detail [id=" + id + ", insertTime=" + insertTime
				+ ", businessMonth=" + businessMonth + ", businessDate="
				+ businessDate + ", skillGroup=" + skillGroup + ", family="
				+ family + ", name=" + name + ", subject1=" + subject1
				+ ", subject2=" + subject2 + ", subject3=" + subject3
				+ ", subject4=" + subject4 + ", count=" + count + ", duration="
				+ duration + "]";
	}
}
