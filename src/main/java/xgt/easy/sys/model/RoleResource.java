/**
 * 
 */
package xgt.easy.sys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gavin
 *
 */
@Entity
@Table(name="sys_role_resource")
public class RoleResource {
	
	@Id
	private int id;
	
	@Column(name="role_id")
	private int roleId;
	@Column(name="resource_id")
	private int resourceId;
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	
}
