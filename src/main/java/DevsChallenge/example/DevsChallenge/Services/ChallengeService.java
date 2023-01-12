package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Challenge;

import java.util.List;

public interface ChallengeService {
    Challenge creer(Challenge challenge);

    List<Challenge> afficher();

    Challenge modifier(Long id, Challenge challenge);

    String supprimer(Long id);

    Challenge ChallengeParId(Long id);
}
