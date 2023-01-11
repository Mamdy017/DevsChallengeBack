package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Repositories.CategorieRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategoriesService{
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
    public Categories modifier(Long id,Categories categories) {
        return this.categorieRepository.findById(id).map(cate->{
            cate.setNom(categories.getNom());
            cate.setDescription(categories.getDescription());
            return categorieRepository.save(cate);
        }).orElseThrow(()-> new  RuntimeException("Catégories non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        this.categorieRepository.deleteById(id);
        return "Categories supprimé avec succes";
    }
}
