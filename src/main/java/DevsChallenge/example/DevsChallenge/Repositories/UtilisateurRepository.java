package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs, Long> {

    Optional<Utilisateurs> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value="SELECT utilisateur.nom,utilisateur.prenom FROM `user_roles`, utilisateur, roles WHERE user_roles.role_id=2 AND utilisateur.id=user_roles.user_id GROUP BY utilisateur.id",nativeQuery=true)
        //@Query(value="SELECT * from Region,Population where Region=Population.region")
    List<Object[]> userlist();
}
