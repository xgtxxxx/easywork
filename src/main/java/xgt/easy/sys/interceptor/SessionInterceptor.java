/**
 * 
 */
package xgt.easy.sys.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import xgt.easy.common.model.Constants;
import xgt.easy.sys.model.User;

/**
 * @author Gavin
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	public static final String XML_HTTP_REQUEST = "X-Requested-With";
	public static final String XML_HTTP_REQUEST_VALUE = "XMLHttpRequest";
	
	private List<String> igores = new ArrayList<String>();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI();
		
		//ajax 请求?
		if (XML_HTTP_REQUEST_VALUE.equals(request.getHeader(XML_HTTP_REQUEST))) {
			if(isIgnore(path)){
				return true;
			}
			if(hasSession(request.getSession())){
				return true;
			}
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print("{'success' : false, 'message' : 'Session is out of time!'}");
		} else {
			if(isIgnore(path)){
				return true;
			}
			if(hasSession(request.getSession())){
				return true;
			}
			
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		return false;
	}
	
	private boolean isIgnore(String path){
		for (String ignore : igores) {
			if(path.endsWith(ignore)){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasSession(HttpSession session){
		User u = (User) session.getAttribute(Constants.SYS_USER);
		return u==null?false:true;
	}

	/**
	 * @param igores the igores to set
	 */
	public void setIgores(List<String> igores) {
		this.igores = igores;
	}
}
