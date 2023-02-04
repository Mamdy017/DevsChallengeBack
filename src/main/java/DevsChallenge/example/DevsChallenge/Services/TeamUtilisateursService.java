package DevsChallenge.example.DevsChallenge.Services;



import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;

import java.util.List;

public interface TeamUtilisateursService {

    Object creer(TeamUtilisateurs teamUtilisateurs);

    List<TeamUtilisateurs> afficher();

    Message modifier(Long id, TeamUtilisateurs teamUtilisateurs);

    Object supprimer(Long id);

    TeamUtilisateurs trouverTeamParId(Long id);

    List<Utilisateurs> Afficher2();
}
