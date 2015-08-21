/**
 * 
 */
package xgt.easy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xgt.easy.common.utils.ParamMap;
import xgt.easy.sys.dao.UserDao;
import xgt.easy.sys.model.ActiveMenu;
import xgt.easy.sys.model.User;
import xgt.easy.sys.service.UserService;

/**
 * @author Gavin
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUser(String email) {
		String hql = "from User where email=?";
		return userDao.getUser(hql, email);
	}

	@Override
	public User getUser(String email, String password) {
		String hql = "from User where email=? and password=?";
		return userDao.getUser(hql, email, password);
	}

	@Override
	public List<ActiveMenu> listActiveMenu(int userId) {
		String hql = "from ActiveMenu where userId=:userId";
		List<ActiveMenu> l = userDao.listActiveMenus(hql,ParamMap.createParamMap("userId", userId));
		if(l==null){
			l = new ArrayList<ActiveMenu>();
		}
		return l;
	}

}
