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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Gavin
 *
 */
@Entity
@Table(name="sys_resource")
public class Resource {
	public interface MainView {};  
    public interface SubView extends MainView {}; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(MainView.class)  
	private int id;
	@Column
	@JsonView(MainView.class)  
	private String text;
	@Column
	@JsonView(MainView.class)  
	private String clazz;
	@Column
	@JsonView(MainView.class)  
	private String xtype;
	@Column
	@JsonView(MainView.class)  
	private String iconCls;
	@Column
	@JsonView(SubView.class)  
	private boolean leaf = false;
	@Column
	private int level;
	
	@Transient
	@JsonView(SubView.class) 
	private boolean isActive = false;
	
	@ManyToOne(cascade=CascadeType.REFRESH,optional=true)
    @JoinColumn(name = "parent_id") 
	@JsonIgnore
	private Resource parent;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
	@JsonView(SubView.class)  
	private Set<Resource> children;
	
	@ManyToMany(mappedBy="resources",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Role> roles;

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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the children
	 */
	public Set<Resource> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<Resource> children) {
		this.children = children;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the xtype
	 */
	public String getXtype() {
		return xtype;
	}

	/**
	 * @param xtype the xtype to set
	 */
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the parent
	 */
	public Resource getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Resource parent) {
		this.parent = parent;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
