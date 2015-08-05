/**
 * 
 */
package xgt.easy.email.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import xgt.easy.email.model.EmailMessage;
import xgt.easy.email.service.EmailMessageService;

/**
 * @author Gavin
 *
 */
@Service
public class EmailMessageServiceImpl implements EmailMessageService {
	@Override
	public void saveMessage(EmailMessage message) {

	}

	@Override
	public List<EmailMessage> listMessage() {
		return null;
	}

}
