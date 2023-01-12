package DevsChallenge.example.DevsChallenge.Services;


import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;

import java.util.List;

public interface SolutionService {

    Object creer(Solution solution, Utilisateurs utilisateurs, Challenge challenge);

    List<Solution> afficher();

    Solution modifier(Long id, Solution solution);

    String supprimer(Long id);


    //List<Solution> findByUserIdAndChallengeId(Long userId, Long challengeId);
}
