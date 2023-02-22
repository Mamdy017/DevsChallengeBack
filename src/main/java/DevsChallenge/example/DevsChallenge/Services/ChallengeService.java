package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.critere;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;

public interface ChallengeService {
    Message creer(Challenge challenge) throws MessagingException;

    List<Challenge> afficher();

    Message modifier(Long id, Challenge challenge);

    Set<critere> getCriteriaByChallengeId(Long challengeId);

    String supprimer(Long id);

    Challenge ChallengeParId(Long id);

    Message modifierEtat1(Long id);
}
