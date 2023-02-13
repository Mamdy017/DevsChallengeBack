package DevsChallenge.example.DevsChallenge.Services;



import DevsChallenge.example.DevsChallenge.Models.Commentaire;

import java.util.List;

public interface CommentaireService {
    Commentaire creer(Commentaire commentaire);

    List<Commentaire> afficher();

    List<Commentaire> findByQuestionId(Long questionId);

    Commentaire modifier(Long id, Commentaire commentaire);

    String supprimer(Long id);

    Commentaire commantaireParId(Long id);



    Commentaire incrementType1(Long questionid);

    Commentaire incrementType2(Long questionid);
}
