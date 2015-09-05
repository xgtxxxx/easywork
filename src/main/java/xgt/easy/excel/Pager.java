/**
 * 
 */
package xgt.easy.excel;

import java.util.Collection;

/**
 * @author Gavin
 *
 */
public interface Pager {
	public String getTitle();
	
	public int getPageNum();
	
	public String[] getHeaders();
	
	public Collection<Object[]> getBody();
	
}
