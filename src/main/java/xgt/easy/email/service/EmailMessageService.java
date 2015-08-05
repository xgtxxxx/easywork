/**
 * 
 */
package xgt.easy.email.service;

import java.util.List;

import xgt.easy.email.model.EmailMessage;

/**
 * @author Gavin
 *
 */
public interface EmailMessageService {
	public void saveMessage(EmailMessage message);
	
	public List<EmailMessage> listMessage();
}
