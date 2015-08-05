/**
 * 
 */
package xgt.easy.template.service;

import java.util.Map;

/**
 * @author Gavin
 *
 */
public interface TemplateService {
	
	public String getTemplateString(String tplPath, Map<String,Object> model);
	
	public String getTemplateString(String tplPath);
}
