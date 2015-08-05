/**
 * 
 */
package xgt.easy.schedule.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.IllegalAddException;
import org.quartz.Job;
import org.quartz.JobListener;

/**
 * @author Gavin
 *
 */
public abstract class AbstractJob implements Job {
	
	private String name;
	
	private boolean igoreMisfire = true;
	
	private boolean durable = true;
	
	private List<JobListener> listeners;
	
	private static ConcurrentHashMap<String,AbstractJob> jobBeans = new ConcurrentHashMap<String,AbstractJob>();
	
	
	public AbstractJob(){
		String clazz = getClass().getName();
		AbstractJob job = getJob(clazz);
		if(job==null){
			jobBeans.put(clazz, this);
		}else{
			throw new IllegalAddException("There is already a job named "+clazz+" in cache!");
		}
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
	 * @return the listeners
	 */
	public List<JobListener> getListeners() {
		return listeners==null?new ArrayList<JobListener>():listeners;
	}
	/**
	 * @param listeners the listeners to set
	 */
	public void setListeners(List<JobListener> listeners) {
		this.listeners = listeners;
	}
	
	public void addListener(JobListener listener){
		this.listeners.add(listener);
	}
	
	public static AbstractJob getJob(String clazz){
		return jobBeans.get(clazz);
	}

	/**
	 * @return the igoreMisfire
	 */
	public boolean isIgoreMisfire() {
		return igoreMisfire;
	}

	/**
	 * @param igoreMisfire the igoreMisfire to set
	 */
	public void setIgoreMisfire(boolean igoreMisfire) {
		this.igoreMisfire = igoreMisfire;
	}

	/**
	 * @return the durable
	 */
	public boolean isDurable() {
		return durable;
	}

	/**
	 * @param durable the durable to set
	 */
	public void setDurable(boolean durable) {
		this.durable = durable;
	}
}
