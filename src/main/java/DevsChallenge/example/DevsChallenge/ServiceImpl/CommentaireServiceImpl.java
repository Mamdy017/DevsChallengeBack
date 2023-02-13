package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Models.Commentaire;
import DevsChallenge.example.DevsChallenge.Repositories.ChallengeRepository;
import DevsChallenge.example.DevsChallenge.Repositories.CommentaireRepository;
import DevsChallenge.example.DevsChallenge.Services.CommentaireService;
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
    private final ChallengeRepository challengeRepository;

    @Override
    public Commentaire creer(Commentaire commentaire) {
        return this.commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> afficher() {
        return this.commentaireRepository.findAll();
    }

    @Override
    public List<Commentaire> findByQuestionId(Long questionId) {
        return commentaireRepository.findByQuestionId(questionId);
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

    @Override
    public Commentaire incrementType1(Long questionid) {
        Commentaire commentaire = commentaireRepository.findById(questionid).get();
        commentaire.incrementType1();
        return commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire incrementType2(Long questionid) {
        Commentaire commentaire = commentaireRepository.findById(questionid).get();
        commentaire.incrementType2();
        return commentaireRepository.save(commentaire);
    }
}
