package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UtilisateurServiceImplements implements Utilisateurservice {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
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
                    use.setPassword(passwordEncoder.encode(users.getPassword()));
                    utilisateurRepository.save(use);
                    return "Modification reussie avec succès";
                }
        ).orElseThrow(() -> new RuntimeException("Cet utilisateur n'existe pas"));
    }

    @Override
    public List<Utilisateurs> Afficher() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateurs userParId(Long idusers) {
        return utilisateurRepository.findById(idusers).get();
    }
}
