/**
 * 
 */
package xgt.easy.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.sys.model.Resource;
import xgt.easy.sys.model.User;

/**
 * @author Gavin
 *
 */
@Transactional
public interface ResourceService {
	
	public List<Resource> listByLevel(int level,User user);

	/**
	 * @param pid
	 * @return
	 */
	public List<Resource> listByParent(int pid);
}
