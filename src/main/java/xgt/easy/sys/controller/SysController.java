/**
 * 
 */
package xgt.easy.sys.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xgt.easy.common.dao.BaseController;
import xgt.easy.common.model.Constants;
import xgt.easy.sys.model.ActiveMenu;
import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.Role;
import xgt.easy.sys.model.RoleResource;
import xgt.easy.sys.model.User;
import xgt.easy.sys.model.UserStatus;
import xgt.easy.sys.service.ResourceService;
import xgt.easy.sys.service.UserService;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Gavin
 *
 */
@Controller
public class SysController extends BaseController{
//	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Map<String,Object> login(String email,String password,HttpServletRequest request){
		User u = userService.getUser(email, password, true);
		if(u==null){
			return fail("No user find!");
		}else{
			if(u.getStatus()==UserStatus.NORMAL.getStatus()){
				HttpSession session = request.getSession();
				session.setAttribute(Constants.SYS_USER, u);
				return success("Login success!");
			}else{
				return fail("Account is invalid!");
			}
		}
	}
	
	@RequestMapping("/home")
	public String home(){
		return "index";
	}
	
	@ResponseBody
	@JsonView(Resource.MainView.class)
	@RequestMapping("/initData")
	public Map<String,Object> initData(HttpServletRequest request){
		User user = getCurrentUser(request);
		List<Resource> rs = this.resourceService.listByLevel(1, user.getRoles());
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("menu", rs);
		result.put("user", user.getName());
		result.put("name", "app");
		result.put("system", getSysInfo());
		return result;
	}
	@ResponseBody
	@JsonView(Resource.SubView.class)
	@RequestMapping("/sys/listSubMenu")
	public List<Resource> listSubMenu(int pid,HttpServletRequest request){
//		List<Resource> list = this.resourceService.listByParent(pid);
		
		User user = this.getCurrentUser(request);
		List<Role> roles = user.getRoles();
		
		List<Resource> list = this.resourceService.listResources(roles,pid);
		
		List<ActiveMenu> menus = this.userService.listActiveMenu(user.getId());
		
		activeMenu(list, menus);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/sys/authurity")
	public Map<String,Object> authurity(int menuId, HttpServletRequest request){
		User user = this.getCurrentUser(request);
		List<RoleResource> auths = this.resourceService.getAuthurity(menuId,user.getId());
		
		Map<String,Object> result = new HashMap<String,Object>();
		boolean readOnly = true;
		for (RoleResource rr : auths) {
			if(!rr.isReadOnly()){
				readOnly = false;
				break;
			}
		}
		
		result.put("readOnly", readOnly);
		
		return result;
	}
	
	private void activeMenu(List<Resource> list, List<ActiveMenu> menus){
		Map<Integer,Resource> leafs = this.findLeafs(list);
		boolean hasActive = false;
		for (ActiveMenu activeMenu : menus) {
			Resource r = leafs.get(activeMenu.getResourceId());
			if(r!=null){
				hasActive = true;
				r.setActive(true);
			}
		}
		if(!hasActive){
			activeFirst(list.get(0));
		}
	}
	
	private Map<Integer,Resource> findLeafs(Collection<Resource> list){
		Map<Integer,Resource> rs = new HashMap<Integer,Resource>();
		for (Resource r : list) {
			if(r.isLeaf()){
				rs.put(r.getId(),r);
			}else{
				rs.putAll(findLeafs(r.getChildren()));
			}
		}
		return rs;
	}
	
	private void activeFirst(Resource resource){
		if(resource.isLeaf()){
			resource.setActive(true);
		}else{
			resource = resource.getChildren().iterator().next();
			activeFirst(resource);
		}
	}
	
	private Map<String,String> getSysInfo(){
		Map<String,String> sysinfo = new HashMap<String,String>();
		sysinfo.put("name", "EasyWork");
		sysinfo.put("version", "1.0.0");
		sysinfo.put("iconUrl", "styles/icons/sys.png");
		return sysinfo;
	}
}
