package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {

    Optional<Utilisateurs> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
