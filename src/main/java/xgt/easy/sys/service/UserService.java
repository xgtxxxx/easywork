/**
 * 
 */
package xgt.easy.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.sys.model.ActiveMenu;
import xgt.easy.sys.model.User;

/**
 * @author Gavin
 *
 */
@Transactional
public interface UserService {
	
	public User getUser(String email);
	
	public User getUser(String email,String password,boolean farceLoad);
	
	public List<ActiveMenu> listActiveMenu(int userId);
}
