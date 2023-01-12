package DevsChallenge.example.DevsChallenge.Services;



import DevsChallenge.example.DevsChallenge.Models.Commentaire;

import java.util.List;

public interface CommentaireService {
    Commentaire creer(Commentaire commentaire);

    List<Commentaire> afficher();

    Commentaire modifier(Long id, Commentaire commentaire);

    String supprimer(Long id);

    Commentaire commantaireParId(Long id);
}
