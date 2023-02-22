package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface Utilisateurservice {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Object[]> userlist();


    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN COLLABORATEUR

    Message Modifier(Long Id, Utilisateurs users);   // LA METHODE PERMETTANT DE MODIFIER UN COLLABORATEUR

    Message ModifierS(Long Id);

    List<Utilisateurs> Afficher();

    // LA METHODE PERMETTANT D'AFFICHER UN COLLABORATEUR

    Utilisateurs userParId(Long idusers);

    public void resetPassword(Utilisateurs user);

    public void updateUserPassword(Utilisateurs user, String newPassword);

    Utilisateurs findByEmail(String userEmail);
}

