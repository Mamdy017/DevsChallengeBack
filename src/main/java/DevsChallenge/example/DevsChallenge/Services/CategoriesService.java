package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Categories;


import java.util.List;

public interface CategoriesService {
    Categories creer(Categories categories);

    List<Categories> afficher();

    Categories modifier(Long id, Categories categories);

    String supprimer(Long id);
}
