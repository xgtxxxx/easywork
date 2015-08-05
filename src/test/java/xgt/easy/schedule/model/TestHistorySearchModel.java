/**
 * 
 */
package xgt.easy.schedule.model;

import java.util.Date;

import org.junit.Test;

/**
 * @author Gavin
 *
 */
public class TestHistorySearchModel {
	@Test
	public void test(){
		HistorySearchModel model = new HistorySearchModel();
		model.setEndRunTime(new Date());
		model.setStart(10);
		model.setJobName("testjob");
		model.setSuccess(true);
		model.setTriggerName("testTrigger");
		System.out.println(model.getQueryString());
	}
}
