package DevsChallenge.example.DevsChallenge.Services;


import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;

import java.util.List;

public interface SolutionService {

    Object creer(Solution solution,  Challenge challenge, Team team, Utilisateurs utilisateurs);

    List<Solution> afficher();

    Solution modifier(Long id, Solution solution);

    String supprimer(Long id);

    List<Solution> findByChallengeId(Long challengeId);
    List<Solution> getSolutionsByChallengeId(Long challengeId);

    //List<Solution> findByUserIdAndChallengeId(Long userId, Long challengeId);
}
