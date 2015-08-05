/**
 * 
 */
package xgt.easy.template.service.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import xgt.easy.template.service.TemplateService;

/**
 * @author Gavin
 *
 */
@Service
public class VelocityTemplateServiceImpl implements TemplateService {
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@PostConstruct
	private void initLoader(){
		velocityEngine.addProperty(VelocityEngine.RESOURCE_LOADER,"classpath");
		velocityEngine.addProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	}

	@Override
	public String getTemplateString(String tplPath, Map<String, Object> model) {
		String result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tplPath,"utf-8",model);
		return result;
	}

	@Override
	public String getTemplateString(String tplPath) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tplPath, "utf-8", null);
	}

}

