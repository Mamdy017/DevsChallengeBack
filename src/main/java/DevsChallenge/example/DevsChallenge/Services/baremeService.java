package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Models.bareme;

import java.util.List;

public interface baremeService {
    bareme creer(bareme bareme1);
    List<bareme> afficher();
    bareme modifier(Long id, bareme bareme1);

    Object supprimer(Long id);
    bareme baremeIp(Long id);
}
