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

import xgt.easy.common.adapters.DateFormat;
import xgt.easy.common.adapters.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Gavin
 *
 */
@Entity
@Table(name="ali_ath4_report")
public class Ath4Report {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	@JsonSerialize(using = DateTimeFormat.class)
	private Date insertTime;
	@Column
	private int businessMonth;
	@Column
	@JsonSerialize(using = DateFormat.class)
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
	private int totalCount;
	@Column
	private double avgDuration;
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
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the avgDuration
	 */
	public double getAvgDuration() {
		return avgDuration;
	}
	/**
	 * @param avgDuration the avgDuration to set
	 */
	public void setAvgDuration(double avgDuration) {
		this.avgDuration = avgDuration;
	}
	
}
