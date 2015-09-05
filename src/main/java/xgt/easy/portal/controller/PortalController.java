/**
 * 
 */
package xgt.easy.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gavin
 *
 */
@Controller
@RequestMapping("/portal")
public class PortalController {
	
	@ResponseBody
	@RequestMapping("/feed")
	public String feed(String data){
		return "Hello "+data;
	}
}
