package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Commentaire;
import DevsChallenge.example.DevsChallenge.Repositories.CommentaireRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class CommentaireServiceImpl implements CommentaireService {
    private final
    CommentaireRepository commentaireRepository;
    @Override
    public Commentaire creer(Commentaire commentaire) {
        return this.commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> afficher() {
        return this.commentaireRepository.findAll();
    }

    @Override
    public Commentaire modifier(Long id, Commentaire commentaire) {
        return null;
    }

    @Override
    public String supprimer(Long id) {
        return null;
    }

    @Override
    public Commentaire commantaireParId(Long id) {
        return null;
    }
}
