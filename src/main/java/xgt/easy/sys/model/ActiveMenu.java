/**
 * 
 */
package xgt.easy.sys.model;

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
@Table(name="sys_active_menu")
public class ActiveMenu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int userId;
	@Column
	private int resourceId;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
}
