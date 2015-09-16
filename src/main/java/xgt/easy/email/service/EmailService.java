/**
 * 
 */
package xgt.easy.email.service;

import xgt.easy.email.model.EmailMessage;

/**
 * @author Gavin
 *
 */
public interface EmailService {
	
	public void send(EmailMessage msg);
	
	/**
	 * 
	 * @param msg : The content of email.
	 * @param subject
	 * @param to : If not only one, you can group them width ','. Not null
	 * @param cc : If not only one, you can group them width ','.
	 * @param bcc : If not only one, you can group them width ','.
	 * @return
	 */
	public void send(String msg, String subject, String to, String cc, String bcc);
	
	public void send(String msg, String subject, String to);
	
	public void send(String msg, String subject, String to, String cc);
	
}
