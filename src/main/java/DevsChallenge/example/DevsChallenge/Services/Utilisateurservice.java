package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface Utilisateurservice {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN COLLABORATEUR

    String Modifier( Utilisateurs users);   // LA METHODE PERMETTANT DE MODIFIER UN COLLABORATEUR

    List<Utilisateurs> Afficher();

    // LA METHODE PERMETTANT D'AFFICHER UN COLLABORATEUR

    Utilisateurs userParId(Long idusers);
}

