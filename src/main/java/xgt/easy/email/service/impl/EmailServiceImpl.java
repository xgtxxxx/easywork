/**
 * 
 */
package xgt.easy.email.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xgt.easy.email.model.EmailAffix;
import xgt.easy.email.model.EmailConfig;
import xgt.easy.email.model.EmailMessage;
import xgt.easy.email.service.EmailConfigService;
import xgt.easy.email.service.EmailMessageService;
import xgt.easy.email.service.EmailService;
import xgt.easy.utils.JsonUtil;

/**
 * @author Gavin
 *
 */
@Service
public class EmailServiceImpl implements EmailService {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	private Map<String,JavaMailSenderImpl> senders;
	
	private JavaMailSenderImpl defaultSender;
	
	@Autowired
	private EmailConfigService emailConfigService;
	
	@Autowired
	private EmailMessageService emailMessageService;
	
	@PostConstruct
	private void init(){
		List<EmailConfig> configs = emailConfigService.getConfig();
		initSender(configs);
	}
	
	private void initSender(List<EmailConfig> configs){
		senders = new HashMap<String,JavaMailSenderImpl>();
		for (EmailConfig config : configs) {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender = new JavaMailSenderImpl();
			mailSender.setHost(config.getHost());
			mailSender.setPort(config.getPort());
			mailSender.setPassword(config.getPassword());
			mailSender.setUsername(config.getUserName());
			mailSender.setDefaultEncoding("UTF-8");
			Properties javaMailProperties = new Properties();
			javaMailProperties.setProperty("mail.smtp.auth", String.valueOf(config.isAuth()));
			javaMailProperties.setProperty("mail.smtp.starttls.enable", String.valueOf(config.isStartTls()));
			mailSender.setJavaMailProperties(javaMailProperties);
			if(config.isDefault()){
				this.defaultSender = mailSender;
			}
			senders.put(config.getUserName(), mailSender);
		}
		if(defaultSender==null){
			throw new NullPointerException("A default email sender should be set!");
		}
	}

	@Override
	public void send(EmailMessage msg) {
		msg.setUserName("");
		JavaMailSenderImpl mailSender = getSender(msg.getUserName());
		MimeMessage mmsg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mmsg, true,"utf-8");
			messageHelper.setText(msg.getContent(), msg.isHtml());
			messageHelper.setTo(msg.getTos());
			messageHelper.setCc(msg.getCcs());
			messageHelper.setBcc(msg.getBccs());
			messageHelper.setSubject(msg.getSubject());
			messageHelper.setFrom(mailSender.getUsername());
			
			if(msg.getAffixs()!=null){
				for (EmailAffix affix : msg.getAffixs()) {
					messageHelper.addAttachment(MimeUtility.encodeWord(affix.getName()), affix.getDataSource());
				}
			}
			
//			mailSender.send(mmsg);
			LOG.info("Send email success : {}",JsonUtil.toJson(msg));
		} catch (MessagingException | UnsupportedEncodingException e) {
			e.printStackTrace();
			LOG.error(e.toString());
		}
	}

	@Override
	public void send(String content, String subject, String to, String cc,
			String bcc) {
		EmailMessage msg = new EmailMessage();
		msg.setContent(content);
		msg.setSubject(subject);
		msg.setTo(to);
		msg.setCc(cc);
		msg.setBcc(bcc);
		
		send(msg);
	}

	@Override
	public void send(String content, String subject, String to) {
		EmailMessage msg = new EmailMessage();
		msg.setContent(content);
		msg.setSubject(subject);
		msg.setTo(to);
		
		send(msg);
	}

	@Override
	public void send(String content, String subject, String to, String cc) {
		EmailMessage msg = new EmailMessage();
		msg.setContent(content);
		msg.setSubject(subject);
		msg.setTo(to);
		msg.setCc(cc);
		
		send(msg);
	}
	
	private JavaMailSenderImpl getSender(String key){
		JavaMailSenderImpl sender = null;
		if(!StringUtils.isEmpty(key)){
			sender = senders.get(key);
		}
		if(sender==null){
			sender = this.defaultSender;
		}
		return sender;
	}
	
}
