/**
 * 
 */
package xgt.easy.ali.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.ali.model.Ath4Detail;

/**
 * @author Gavin
 *
 */
public interface AliService {
	
	public int save(List<Ath4Detail> aths);

	/**
	 * @return
	 */
	@Transactional
	public <T> List<T> list(Class<?> clazz);

	/**
	 * @param ids
	 * @param response
	 */
	@Transactional
	public void export(String ids, HttpServletResponse response);
}
