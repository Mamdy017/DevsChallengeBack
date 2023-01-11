package DevsChallenge.example.DevsChallenge.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvoyeMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailToMultipleRecipients(String subject, String text, List<String> recipients) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(recipients.toArray(new String[0]));
        mailSender.send(message);
    }
    }

