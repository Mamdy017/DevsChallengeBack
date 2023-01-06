package DevsChallenge.example.DevsChallenge.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface Utilisateurservice {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

