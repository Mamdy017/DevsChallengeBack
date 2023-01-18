package DevsChallenge.example.DevsChallenge.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EnvoyeMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailToMultipleRecipients(String subject, String text, List<String> recipients, String htmlTemplate) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.setTo(recipients.toArray(new String[0]));
        helper.setText(htmlTemplate, true);
        mailSender.send(message);
    }

}

