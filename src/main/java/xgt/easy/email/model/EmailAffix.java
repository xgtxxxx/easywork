/**
 * 
 */
package xgt.easy.email.model;

import javax.activation.DataSource;

/**
 * @author Gavin
 *
 */
public class EmailAffix {
	
	/**
	 * @param name
	 * @param path
	 */
	public EmailAffix(String name, DataSource ds) {
		super();
		this.name = name;
		this.dataSource = ds;
	}
	
	public EmailAffix() {
		super();
	}

	private String name;
	
	private DataSource dataSource;

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
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
