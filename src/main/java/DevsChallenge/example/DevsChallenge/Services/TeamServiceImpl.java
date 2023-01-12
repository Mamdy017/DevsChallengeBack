package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService{
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
        return null;
    }

    @Override
    public Object supprimer(Long id) {
        return null;
    }

    @Override
    public Team trouverTeamParId(Long id) {
        return null;
    }
}
