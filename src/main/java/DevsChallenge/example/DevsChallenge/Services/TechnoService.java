package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Technologies;

import java.util.List;

public interface TechnoService {

    Technologies creer(Technologies technologies);

    List<Technologies> afficher();

    Technologies modifier(Long id, Technologies technologies);

    String supprimer(Long id);
}
