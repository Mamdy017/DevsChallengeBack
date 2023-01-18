package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;

import javax.mail.MessagingException;
import java.util.List;

public interface ChallengeService {
    Message creer(Challenge challenge) throws MessagingException;

    List<Challenge> afficher();

    Message modifier(Long id, Challenge challenge);

    String supprimer(Long id);

    Challenge ChallengeParId(Long id);
}
