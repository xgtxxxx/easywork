/**
 * 
 */
package xgt.easy.email;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xgt.easy.email.model.EmailMessage;
import xgt.easy.email.service.EmailService;
import xgt.easy.template.service.TemplateService;

/**
 * @author Gavin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/email/email-test.xml"})
public class TestEmailService {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private TemplateService templateService;
	
	@Ignore
	@Test
	public void sendEmail(){
		emailService.send("This is a test email!", "Test", "343445708@qq.com");
	}
	
	@Ignore
	@Test
	public void sendEmailWithAffixs(){
		EmailMessage msg = new EmailMessage();
		msg.setSubject("Test Affix Email");
		msg.setContent("Test send a email with some affixs!");
		msg.setTo("343445708@qq.com,gangtao.xi@symbio.com");
		DataSource ds = new FileDataSource(new File("D:\\text.xls"));
		msg.addAffix("附件1", ds);
		ds = new FileDataSource("D:\\document\\创建新版本号.png");
		msg.addAffix("附件2", ds);
		
		this.emailService.send(msg);
	}
	
	@Test
	public void sendEmailWithTemplate(){
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("sender", "Gavin");
		
		String content = this.templateService.getTemplateString("/template/NoticeMail.vm", model);
		EmailMessage msg = new EmailMessage();
		msg.setSubject("Test Template Email");
		msg.setContent(content);
		msg.setTo("343445708@qq.com");
		this.emailService.send(msg);
	}
	
}

