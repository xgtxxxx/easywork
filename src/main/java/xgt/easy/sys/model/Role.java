/**
 * 
 */
package xgt.easy.sys.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Gavin
 *
 */
@Entity
@Table(name="sys_role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@ManyToMany(mappedBy="roles",cascade=CascadeType.ALL)
	private Set<User> users;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="sys_role_resource",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="resource_id")
    )
	private Set<Resource> resources;

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
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * @return the resources
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
