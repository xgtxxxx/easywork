/**
 * 
 */
package xgt.easy.schedule.quartz;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.quartz.utils.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gavin
 *
 */
public class SpringConnectionProvider implements ConnectionProvider {
	private static final Logger LOG = LoggerFactory.getLogger(SpringConnectionProvider.class);

	private DataSource dataSource;
	
	private String dataSourceName;
	
	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public void shutdown() throws SQLException {
		LOG.debug("==========do shutdown===========");
		dataSource = null;
	}

	@Override
	public void initialize() throws SQLException {
		LOG.debug("==========do initialize===========");
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param dataSourceName the dataSourceName to set
	 */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/**
	 * @return the dataSourceName
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

}
