package DevsChallenge.example.DevsChallenge.Services;


import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SolutionService {

    Object creer(Solution solution,  Challenge challenge, Team team, Utilisateurs utilisateurs);

    Object creerPayuser(Solution solution, Challenge challenge, Utilisateurs utilisateurs);

    List<Solution> afficher();

    Solution modifier(Long id, Solution solution);

    String supprimer(Long id);

    List<Solution> findByChallengeId(Long challengeId);
    List<Solution> getSolutionsByChallengeId(Long challengeId);

    List<Solution> getNonEtat1Solutions();


    //List<Solution> findByUserIdAndChallengeId(Long userId, Long challengeId);
}
