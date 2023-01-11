package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Repositories.ChallengeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService{
    private final ChallengeRepository challengeRepository;
    @Override
    public Challenge creer(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public List<Challenge> afficher() {
        return challengeRepository.findAll();
    }

    @Override
    public Challenge modifier(Long id, Challenge challenge) {
        return this.challengeRepository.findById(id).map(chal ->{
            chal.setCritere(challenge.getCritere());
            chal.setDatedebut(challenge.getDatedebut());
            chal.setDatefin(challenge.getDatefin());
            chal.setTitre(challenge.getTitre());
            return challengeRepository.save(chal);
        }) .orElseThrow(()-> new  RuntimeException("Technologies non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        this.challengeRepository.deleteById(id);
        return "Challenge supprime avec succes";
    }
}
