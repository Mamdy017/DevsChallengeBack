package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamUtilisateurRepository extends JpaRepository<TeamUtilisateurs, Long> {
    TeamUtilisateurs findByTeamAndUtilisateurs(Team team, Utilisateurs utilisateurs);
}
