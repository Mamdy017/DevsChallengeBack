package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Repositories.CategorieRepository;
import DevsChallenge.example.DevsChallenge.Services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategoriesService {
    @Autowired
    private final CategorieRepository categorieRepository;

    @Override
    public Categories creer(Categories categories) {
        return categorieRepository.save(categories);
    }

    @Override
    public List<Categories> afficher() {
        return categorieRepository.findAll();
    }


    @Override
    public Message modifier(Long id, Categories categories) {
        try {
            Categories category = categorieRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            category.modifier(categories.getNom());
            categorieRepository.save(category);
            System.out.println(LocalDate.now());
            return (Message) Message.set("ajout effectue", true);
        } catch (RuntimeException e) {
            return (Message) Message.set("une erreur s'est produite", false);
        }
    }

    @Override
    public String supprimer(Long id) {
        this.categorieRepository.deleteById(id);
        return "Categories supprimé avec succes";
    }
}
