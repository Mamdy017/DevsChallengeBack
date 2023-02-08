package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import DevsChallenge.example.DevsChallenge.Repositories.TeamUtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

   private final TeamUtilisateurRepository teamUtilisateurRepository;
    @Override
    public Team creer(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> afficher() {
        return this.teamRepository.findAll();
    }

    @Override
    public Team modifier(Long id, Team team) {
        return this.teamRepository.findById(id).map(tea->{
            tea.setNom(team.getNom());
            return teamRepository.save(tea);
        }).orElseThrow(()->new RuntimeException(String.valueOf(Message.set("Team non trouvé",true))));
    }

    @Override
    public Object supprimer(Long id) {
        this.teamRepository.deleteById(id);
        return Message.set("Team supprimé avc succès",true);
    }

    public Message createTeamAndAddCreator(String teamName, Utilisateurs creator, Challenge challenge) {
        Team existingTeam = teamRepository.findByNom(teamName);
        if (existingTeam != null) {
            return Message.set("Une équipe avec ce nom existe déjà pour ce challenge", false);
        }

        Date dateFin = challenge.getDatefin();

        if (dateFin != null && dateFin.before(new Date())) {
            return Message.set("Le challenge est déjà terminé", false);
        }

        if (teamUtilisateurRepository.existsByUtilisateursAndChallenge(creator, challenge)) {
            return Message.set("Vous avez déjà créé une équipe pour ce challenge", false);
        }

        Team team = new Team();
        team.setNom(teamName);
        TeamUtilisateurs teamUtilisateurs = new TeamUtilisateurs();
        teamUtilisateurs.setUtilisateurs(creator);
        teamUtilisateurs.setChallenge(challenge);
        teamUtilisateurs.setType(1);
        teamUtilisateurs.getTeam().add(team);
        teamRepository.save(team);
        teamUtilisateurRepository.save(teamUtilisateurs);

        return Message.set("Equipe créer avec succès", true);
    }


    @Override
    public Team trouverTeamParId(Long id) {
        return teamRepository.findById(id).get();
    }
}
