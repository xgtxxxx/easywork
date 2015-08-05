/**
 * 
 */
package xgt.easy.schedule.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xgt.easy.common.dao.BaseController;
import xgt.easy.common.model.Pager;
import xgt.easy.schedule.model.HistorySearchModel;
import xgt.easy.schedule.model.JobHistory;
import xgt.easy.schedule.service.JobHistoryService;

/**
 * @author Gavin
 *
 */
@Controller
@RequestMapping("/jobHistory")
public class JobHistoryController extends BaseController {

	@Autowired
	private JobHistoryService jobHistoryService;

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Map<String, Object> search(HistorySearchModel model) {
		Pager<JobHistory> pager = this.jobHistoryService.search(model);
		return result(pager.getResults(), pager.getTotalRows());
	}

}
