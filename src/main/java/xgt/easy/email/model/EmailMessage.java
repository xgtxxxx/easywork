/**
 * 
 */
package xgt.easy.email.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.springframework.util.StringUtils;


/**
 * @author Gavin
 *
 */
public class EmailMessage {
	private String to;
	
	private String cc;
	
	private String bcc;
	
	private String subject;
	
	private String content;
	
	private List<EmailAffix> affixs;
	
	private Date sendTime;
	
	private String sender;
	
	private String userName;
	
	private boolean html = true;
	
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	
	public String[] getTos() {
		return StringUtils.isEmpty(to)?new String[]{}:to.split(",");
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}
	
	public String[] getCcs() {
		return StringUtils.isEmpty(cc)?new String[]{}:cc.split(",");
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}
	
	public String[] getBccs() {
		return StringUtils.isEmpty(bcc)?new String[]{}:bcc.split(",");
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the affixs
	 */
	public List<EmailAffix> getAffixs() {
		return affixs;
	}

	/**
	 * @param affixs the affixs to set
	 */
	public void setAffixs(List<EmailAffix> affixs) {
		this.affixs = affixs;
	}
	
	public void addAffix(String name, DataSource ds){
		if(this.affixs==null){
			this.affixs = new ArrayList<EmailAffix>();
		}
		this.affixs.add(new EmailAffix(name,ds));
	}

	/**
	 * @return the sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the html
	 */
	public boolean isHtml() {
		return html;
	}

	/**
	 * @param html the html to set
	 */
	public void setHtml(boolean html) {
		this.html = html;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This userName reference this userName of EmailConfig
	 * @see xgt.easy.email.model.EmailConfig
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
