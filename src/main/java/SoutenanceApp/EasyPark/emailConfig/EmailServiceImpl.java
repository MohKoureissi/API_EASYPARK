package SoutenanceApp.EasyPark.emailConfig;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailInterface{
      /**
     *
     */
    @Autowired
    private  JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public  String sendSimpleMail(EmailDetails details) {
        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getDestinateur());
            mailMessage.setText(details.getMsg());
            mailMessage.setSubject(details.getObjet());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Email envoye avec succes";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            throw new RuntimeException("Error lors de l'envoi du Mail") ;
        }
    }
}
