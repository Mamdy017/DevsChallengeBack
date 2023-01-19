package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.EmailConstructor;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UtilisateurServiceImplements implements Utilisateurservice {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public List<Object[]> userlist() {
        return utilisateurRepository.userlist();
    }

    @Override
    public String Supprimer(Long id_users) {
        return null;
    }

    @Override
    public String Modifier(Long Id, Utilisateurs users) {
        return utilisateurRepository.findById(Id).map(
                use ->{
                    use.setEmail(users.getEmail());
                    use.setUsername(users.getUsername());
                    use.setNom(users.getNom());
                    use.setPrenom(users.getPrenom());
                    use.setProfile(users.getProfile());
                    use.setMois(users.getMois());
                    use.setPassword(passwordEncoder.encode(users.getPassword()));
                    System.out.println("####################################");
                    System.out.println(use);
                    utilisateurRepository.save(use);
                    return "Modification reussie avec succès";
                }
        ).orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Cet utilisateur n'existe pas",true))));
    }

    @Override
    public List<Utilisateurs> Afficher() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateurs userParId(Long idusers) {
        return utilisateurRepository.findById(idusers).get();
    }
    @Override
    public Utilisateurs findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public void resetPassword(Utilisateurs user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        utilisateurRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));
    }

    @Override
    public void updateUserPassword(Utilisateurs user, String newPassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        utilisateurRepository.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newPassword));

    }
}
