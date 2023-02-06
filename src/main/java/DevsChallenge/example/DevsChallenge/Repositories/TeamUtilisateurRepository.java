package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeamUtilisateurRepository extends JpaRepository<TeamUtilisateurs, Long> {
    TeamUtilisateurs findByTeamAndUtilisateurs(Team team, Utilisateurs utilisateurs);

    Optional<TeamUtilisateurs> findByUtilisateursAndType(Utilisateurs utilisateurs, int i);
    Optional<TeamUtilisateurs> findByUtilisateursAndTypeAndChallenge(Utilisateurs utilisateurs, int i, Challenge challenge);

    List<TeamUtilisateurs> findByChallengeAndTeam(Challenge challenge, Team team);

    Set<TeamUtilisateurs> findByChallengeAndUtilisateurs(Challenge challenge, Utilisateurs utilisateurs);

}
