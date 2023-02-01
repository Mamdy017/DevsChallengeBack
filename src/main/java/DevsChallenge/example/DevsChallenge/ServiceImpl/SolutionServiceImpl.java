package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;

import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Repositories.SolutionRepository;
import DevsChallenge.example.DevsChallenge.Services.SolutionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
   /*@Override
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
*/


    @Override
    public ResponseEntity<Object> creer(Solution solution, Challenge challenge, Team team) {
        Boolean T = solutionRepository.existsByTeamAndChallenge(team, challenge);

        Date today = new Date();
        if (challenge.getDatefin().before(today)) {
            return new ResponseEntity<>(Message.set("Le challenge est fermé, vous ne pouvez pas soumettre de solution", true), HttpStatus.OK);
        } else if (!T) {
            if (solution.getLienGithub().isEmpty() && solution.getSource().isEmpty()) {
                return new ResponseEntity<>(Message.set("Veuillez ajouter un lien Github ou une source", true), HttpStatus.OK);
            } else if (!solution.getLienGithub().isEmpty() && !solution.getSource().isEmpty()) {
                return new ResponseEntity<>(Message.set("Veuillez n'ajouter qu'un lien Github ou une source", true), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(solutionRepository.save(solution), HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>(Message.set("Vous avez déjà ajouté une solution", true), HttpStatus.OK);
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
