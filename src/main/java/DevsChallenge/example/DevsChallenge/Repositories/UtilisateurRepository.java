package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {

  Utilisateurs findByUsername(String username);

    Optional<Utilisateurs> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Utilisateurs findByEmail(String email);

    @Query(value="SELECT utilisateur.email FROM `user_roles`, utilisateur, roles WHERE user_roles.role_id=2 AND utilisateur.id=user_roles.user_id GROUP BY utilisateur.id",nativeQuery=true)
    List<Object[]> userlist();
}
