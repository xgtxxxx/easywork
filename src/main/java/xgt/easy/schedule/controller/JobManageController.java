/**
 * 
 */
package xgt.easy.schedule.controller;

import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xgt.easy.common.dao.BaseController;
import xgt.easy.schedule.model.TaskTrigger;
import xgt.easy.schedule.model.Taskjob;
import xgt.easy.schedule.service.ScheduleJobService;

/**
 * @author Gavin
 *
 */
@Controller
@RequestMapping("/job")
public class JobManageController extends BaseController {

	@Autowired
	private ScheduleJobService jobService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> listJobs() {
		List<Taskjob> jobs = this.jobService.listJobs();
		return result(jobs, jobs.size());
	}

	@ResponseBody
	@RequestMapping(value = "/listJobTrigger", method = RequestMethod.GET)
	public Map<String, Object> listJobTrigger(String jobName) {
		List<TaskTrigger> triggers = this.jobService.listTriggers(jobName);
		return result(triggers, triggers.size());
	}

	@ResponseBody
	@RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
	public Map<String, Object> pauseJob(String jobName) {
		if (this.jobService.pauseJob(jobName)) {
			return success("Pause Job Success!");
		} else {
			return fail("Pause Job Failed!");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
	public Map<String, Object> resumeJob(String jobName) {
		if (this.jobService.resumeJob(jobName)) {
			return success("Resume Job Success!");
		} else {
			return fail("Resume Job Failed!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pauseTrigger", method = RequestMethod.POST)
	public Map<String, Object> pauseTrigger(String triggerName) {
		if (this.jobService.pauseTrigger(triggerName)) {
			return success("Pause "+triggerName+" Success!");
		} else {
			return fail("Pause "+triggerName+" Failed!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/resumeTrigger", method = RequestMethod.POST)
	public Map<String, Object> resumeTrigger(String triggerName) {
		if (this.jobService.resumeTrigger(triggerName)) {
			return success("Resume "+triggerName+" Success!");
		} else {
			return fail("Resume "+triggerName+" Failed!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeTrigger", method = RequestMethod.POST)
	public Map<String, Object> removeTrigger(String triggerName) {
		if (this.jobService.removeTrigger(triggerName)) {
			return success("Remove "+triggerName+" Success!");
		} else {
			return fail("Remove "+triggerName+" Failed!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/newTrigger", method = RequestMethod.POST)
	public Map<String, Object> newTrigger(TaskTrigger taskTrigger,String unit,String jobName) {
		try{
			JobKey jobKey = new JobKey(jobName);
			taskTrigger.setJobKey(jobKey);
			if(TaskTrigger.SIMPLE.equals(taskTrigger.getType())){
				switch(unit){
					case TaskTrigger.SECOND : break;
					case TaskTrigger.MINUTE : taskTrigger.setRepectInterval(taskTrigger.getRepectInterval()*60); break;
					case TaskTrigger.HOUR : taskTrigger.setRepectInterval(taskTrigger.getRepectInterval()*60*60); break;
					case TaskTrigger.DAY : taskTrigger.setRepectInterval(taskTrigger.getRepectInterval()*60*60*24); break;
				}
			}
			this.jobService.addTrigger(taskTrigger);
		}catch(Exception e){
			e.printStackTrace();
			return fail(e.getMessage());
		}
		return success("Add new trigger success!");
	}
	
	@ResponseBody
	@RequestMapping(value = "/newJob", method = RequestMethod.POST)
	public Map<String, Object> newJob(Taskjob taskJob, String jobName) {
		if(this.jobService.checkJobExistByClass(taskJob.getClazz())){
			return fail("There is alread a job exist(class : "+taskJob.getClazz()+")!");
		}
		try{
			taskJob.setJobkey(new JobKey(jobName));
			this.jobService.addJob(taskJob);
		}catch(Exception e){
			e.printStackTrace();
			return this.fail(e.getMessage());
		}
		return this.success("Add new job success!");
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public Map<String, Object> deleteJob(String jobName) {
		try{
			this.jobService.deleteJob(jobName);
		}catch(Exception e){
			e.printStackTrace();
			return this.fail(e.getMessage());
		}
		return this.success("Delete job("+jobName+") success!");
	}
	
	@ResponseBody
	@RequestMapping(value = "/startJobNow", method = RequestMethod.POST)
	public Map<String, Object> startJobNow(String jobName) {
		try{
			this.jobService.startJobNow(jobName);
		}catch(Exception e){
			e.printStackTrace();
			return this.fail(e.getMessage());
		}
		return this.success("Start job("+jobName+") success!");
	}
	
}
