package DevsChallenge.example.DevsChallenge.Messages;


import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Component
@ToString
public class EmailConstructor {

    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;

    public MimeMessagePreparator constructNewUserEmail(Utilisateurs user) {
        if (user != null) {
            Context context = new Context();
            context.setVariable("user", user);
            String text = templateEngine.process("newUserEmailTemplate", context);
            MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                    email.setPriority(1);
                    email.setTo(user.getEmail());
                    email.setSubject("Bienvenue dans l'Application Akilina");
                    email.setText(text, true);
                    String supportEmail = env.getProperty("support.email");
                    if (supportEmail != null) {
                        email.setFrom(new InternetAddress(supportEmail));
                    }
                }
            };
            return messagePreparator;
        } else {
            throw new IllegalArgumentException("user cannot be null");
        }
    }

    public MimeMessagePreparator constructResetPasswordEmail(Utilisateurs user, String password) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text = templateEngine.process("resetPasswordEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("New Password - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructUpdateUserProfileEmail(Utilisateurs user) {
        Context context = new Context();
        context.setVariable("user", user);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(user.getEmail());
                email.setSubject("Profile Update - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }
}