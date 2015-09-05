/**
 * 
 */
package xgt.easy.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xgt.easy.common.dao.BaseController;
import xgt.easy.sys.service.ResourceService;

/**
 * @author Gavin
 *
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;
	
}
