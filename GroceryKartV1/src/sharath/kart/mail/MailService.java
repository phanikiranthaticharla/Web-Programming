package sharath.kart.mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import sharath.kart.MongoDBRegisterService;
import sharath.kart.Success;
import sharath.kart.constants.Constants;

@Path("/mailservices")
public class MailService {
	final static Logger log= Logger.getLogger(MailService.class);
	@Path("/mailuser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)

	public Response mailUser(MultivaluedMap<String, String> formParam) throws Exception {
		log.info(formParam);
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Success s = new Success();
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.mail_id,Constants.mail_pass);
			}
		});
		
		String from = formParam.getFirst("from");    
	    String subject = formParam.getFirst("subject");
	    String  content = formParam.getFirst("content");
//	    String data = "We Received your Request \n Your Message is : \n"+content+"\n We will get back to you soon!!!";
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.mail_id+"@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(from));
			message.setSubject(subject);
			message.setText(content);
			
			log.info(message);
			Transport.send(message);

			s.setSuccess("true");

		} catch (MessagingException e) {
			log.error(e);
			s.setSuccess("false");
			throw new RuntimeException(e);
		}
		log.info(s);
		return Response.ok().entity(s).build();
	}

}
