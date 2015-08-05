/**
 * 
 */
package xgt.easy.email.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import xgt.easy.email.model.EmailConfig;
import xgt.easy.email.service.EmailConfigService;

/**
 * @author Gavin
 *
 */
@Service
public class EmailConfigServiceImpl implements EmailConfigService {

	@Override
	public List<EmailConfig> getConfig() {
		List<EmailConfig> configs = new ArrayList<EmailConfig>();
		EmailConfig config = new EmailConfig();
		config.setAuth(true);
		config.setHost("smtp.163.com");
		config.setLog(false);
		config.setPassword("xgtxxxx240720");
		config.setUserName("xgtxxxx@163.com");
		config.setPort(25);
		config.setDefault(true);
		configs.add(config);
		return configs;
	}

}
