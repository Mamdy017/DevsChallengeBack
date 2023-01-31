package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.TeamUtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.TeamUtilisateursService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamUtilisateursServiceImpl implements TeamUtilisateursService {
    private final TeamUtilisateurRepository teamUtilisateurRepository;
    @Override
    public Object creer(TeamUtilisateurs teamUtilisateurs) {
        this.teamUtilisateurRepository.save(teamUtilisateurs);
        return Message.set("Demande d'ajoout au team envoyée avec succès",true);
    }



    @Override
    public List<TeamUtilisateurs> afficher() {
        return teamUtilisateurRepository.findAll();
    }

    @Override
    public Message modifier(Long id, TeamUtilisateurs teamUtilisateurs) {
        return this.teamUtilisateurRepository.findById(id).map(tu->{
            tu.setType(teamUtilisateurs.getType());
            this.teamUtilisateurRepository.save(tu);
            return Message.set(" Demande confirmée",true);
        }).orElseThrow(()->new RuntimeException(String.valueOf(Message.set("Demande non trouvée",true))));
    }

    @Override
    public Object supprimer(Long id) {
        this.teamUtilisateurRepository.deleteById(id);
        return Message.set("demande annulé avec succes",true);
    }

    @Override
    public TeamUtilisateurs trouverTeamParId(Long id) {
        return teamUtilisateurRepository.findById(id).get();
    }
}
