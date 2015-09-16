/**
 * 
 */
package xgt.easy.ali.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import xgt.easy.ali.model.Ath4Detail;
import xgt.easy.ali.model.Search;

/**
 * @author Gavin
 *
 */
public interface AliService {
	
	public int save(List<Ath4Detail> aths);

	@Transactional
	public List<Ath4Detail> list(Search searchfield);
	/**
	 * @param ids
	 * @param response
	 */
	@Transactional
	public void export(Search search, HttpServletResponse response);

	/**
	 * @param search
	 * @return
	 */
	@Transactional
	public List<Ath4Detail> listDetail(Search search);
}
