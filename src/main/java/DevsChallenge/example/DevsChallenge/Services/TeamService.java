package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;

import java.util.List;

public interface TeamService {
    Team creer(Team team);

    List<Team> afficher();

    Team modifier(Long id, Team team);

    Object supprimer(Long id);

    Team trouverTeamParId(Long id);

    Message createTeamAndAddCreator(String teamName, Utilisateurs creator, Challenge challenge);
}
