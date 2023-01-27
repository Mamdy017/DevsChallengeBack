package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Question;
import DevsChallenge.example.DevsChallenge.Models.critere;

import java.util.List;

public interface critereService {
     Message creer(critere critere1);
    List<critere> afficher();
    critere modifier(Long id, critere critere1);

    Object supprimer(Long id);
    critere critereIp(Long id);
}
