package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import DevsChallenge.example.DevsChallenge.Services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
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

    @Override
    public Team trouverTeamParId(Long id) {
        return teamRepository.findById(id).get();
    }
}
