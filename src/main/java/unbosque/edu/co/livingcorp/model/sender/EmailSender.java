package unbosque.edu.co.livingcorp.model.sender;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
public class EmailSender {

        public void sendEmail(String to, String subject, String content) {

            String from = "livingcorp333@gmail.com";
            final String username = "livingcorp333@gmail.com";
            final String password = "jtrw akqg njls mbyx";


            String host = "smtp.gmail.com";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");


            Session session = Session.getInstance(props,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
                message.setSubject(subject);
                message.setText(content);


                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }

    }



