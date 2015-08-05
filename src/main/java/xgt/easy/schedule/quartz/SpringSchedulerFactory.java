/**
 * 
 */
package xgt.easy.schedule.quartz;

import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.DBConnectionManager;

/**
 * This just a adapter for spring, and you can user your connection provider by spring Ioc.
 * @author Gavin
 *
 */
public class SpringSchedulerFactory extends StdSchedulerFactory {

	public void setConnectionProvider(SpringConnectionProvider connectionProvider){
		DBConnectionManager dbMgr = DBConnectionManager.getInstance();
		dbMgr.addConnectionProvider(connectionProvider.getDataSourceName(), connectionProvider);
	}
	
}
