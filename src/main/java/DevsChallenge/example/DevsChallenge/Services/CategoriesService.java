package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;


import java.util.List;

public interface CategoriesService {
    Categories creer(Categories categories);

    List<Categories> afficher();

    Message modifier(Long id, Categories categories);

    String supprimer(Long id);
}
