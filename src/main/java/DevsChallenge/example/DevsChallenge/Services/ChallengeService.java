package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;

import java.util.List;

public interface ChallengeService {
    Message creer(Challenge challenge);

    List<Challenge> afficher();

    Message modifier(Long id, Challenge challenge);

    String supprimer(Long id);

    Challenge ChallengeParId(Long id);
}
