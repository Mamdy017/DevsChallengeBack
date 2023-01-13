package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;

import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.SolutionRepository;
import DevsChallenge.example.DevsChallenge.Services.SolutionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
   @Override
    public Object creer(Solution solution, Utilisateurs utilisateurs, Challenge challenge, Team team) {

        Boolean T= solutionRepository.existsByTeamAndChallenge(team,challenge);
        Boolean b = solutionRepository.existsByUtilisateursAndChallenge(utilisateurs, challenge);
        if(!b){
            return solutionRepository.save(solution) ;
        }
        if (!T){
            return solutionRepository.save(solution);
        }

        else
        {

            return Message.set(
                    "Vous avez déjà ajouté une solution",
                    true
            );//message;
        }

    }


    @Override
    public List<Solution> afficher() {
        return solutionRepository.findAll();
    }

    @Override
    public Solution modifier(Long id, Solution solution) {
        return solutionRepository.findById(id).map( s ->{
            s.setNom(solution.getNom());
            s.setSource(solution.getSource());
            s.setPoint(solution.getPoint());
            s.setLienGithub(solution.getLienGithub());
            return solutionRepository.save(s);
        }).orElseThrow(()-> new  RuntimeException("Question  non trouvée"));
    }


    @Override
    public String supprimer(Long id) {
        return "Solution supprimée";
    }




}
