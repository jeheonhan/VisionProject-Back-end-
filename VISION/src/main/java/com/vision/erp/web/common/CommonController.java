package com.vision.erp.web.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vision.erp.common.SendSMS;
import com.vision.erp.service.domain.Email;
import com.vision.erp.service.domain.SMS;

@RestController
public class CommonController {
	
	//Sample : {"recipient":"qhdqhdekd261@gmail.com","subject":"메일 왔냐?","contents":"열심히하는 척 노노해"}
	@RequestMapping(value = "/common/sendEmail", method = RequestMethod.POST)
	public void sendEmail(@RequestBody Email email) throws Exception{
		System.out.println("mailService");
		
		String host = "smtp.naver.com";
		
		final String username = "glossator@naver.com";
		final String password = "vision1234";
		int port = 465;
		
		//메일내용
		String recipient = email.getRecipient();
		String subject = email.getSubject();
//		String body = email.getContents();
	
		String body = "<html lang=\"kor\" dir=\"ltr\">\n" + 
				"  <head>\n" + 
				"    <meta charset=\"utf-8\">\n" + 
				"    <title></title>\n" + 
				"    <style media=\"screen\">\n" + 
				"      .hr{\n" + 
				"        border: 0;\n" + 
				"        height: 3px;\n" + 
				"        background: #DF4D4D;\n" + 
				"      }\n" + 
				"      .img{\n" + 
				"        align:center;\n" + 
				"      }\n" + 
				"      #logo{\n" + 
				"        height:50px;\n" + 
				"      }\n" + 
				"    </style>\n" + 
				"  </head>\n" + 
				"  <body>\n" + 
				"    <div class=\"img\">\n" + 
				"      <img id=\"logo\" src=\"https://1.bp.blogspot.com/-S3YOEyM-Cac/XU5Zf4He63I/AAAAAAAAALg/GP2Fyr7N99Y2JP1MIcm9knNRpoXcclKUgCLcBGAs/s320/VisionLogo.png\" alt=\"이미지\">\n" + 
				"      &nbsp;<b><font size=\"130px\" text=\"bold\">비전 그룹웨어</font></b>\n" + 
				"    </div>\n" + 
				"\n" + 
				"    <hr class=\"hr\"/>\n" + 
				"    <h1>"+subject+"</h1>\n" + 
				"    <br>\n" + 
				"    <br>\n" + 
				"    <br>\n" + 
				"    <h3>"+email.getContents()+"</h3>\n" + 
				"    <br>\n" + 
				"    <br>\n" + 
				"    <hr class=\"hr\"/>\n" + 
				"    <br>\n" + 
				"\n" + 
				"\n" + 
				"  </body>\n" + 
				"</html>\n" + 
				"";
		
		Properties props = System.getProperties();
		
		//SMTP 서버정보 설정
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.trust", host);
		
		//Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		String un=username;
		String pw=password;
		protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
			return new javax.mail.PasswordAuthentication(un, pw);
		}
		});
		session.setDebug(true);
		
		
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress("glossator@naver.com"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		    mimeMessage.setSubject(MimeUtility.encodeText(subject, "UTF-8","B"));
//		    mimeMessage.setText(body);
		    mimeMessage.setContent(body, "text/html; charset=UTF-8");
		    
		   
		    // 메일을 발신한다
		    Transport.send(mimeMessage);
		} catch (Exception e) {
		   	e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/common/sendSMS")
	public void sendSMS(@RequestBody SMS sms) throws Exception{
		
		SendSMS sendSMS = SendSMS.getSendSMSInstance();
		sendSMS.sendSMS(sms);
	}
}
