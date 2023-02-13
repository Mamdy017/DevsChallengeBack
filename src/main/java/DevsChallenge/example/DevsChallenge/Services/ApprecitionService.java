package DevsChallenge.example.DevsChallenge.Services;



import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Appreciation;

import java.util.List;

public interface ApprecitionService {
    Object creer(Appreciation appreciation);

    List<Appreciation> afficher();

    Message modifier(Long id, Appreciation appreciation);

    Object supprimer(Long id);

    Appreciation trouverAppParId(Long id);

    Appreciation incrementType1(Long commentId);

    Appreciation incrementType2(Long commentId);
}
