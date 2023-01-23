package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Models.bareme;
import DevsChallenge.example.DevsChallenge.Repositories.baremeRepository;
import DevsChallenge.example.DevsChallenge.Services.baremeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class baremeServiceImpl implements baremeService {
    @Autowired
    baremeRepository baremeRepository1;
    @Override
    public bareme creer(bareme bareme1) {
        return baremeRepository1.save(bareme1);
    }

    @Override
    public List<bareme> afficher() {
        return baremeRepository1.findAll();
    }

    @Override
    public bareme modifier(Long id, bareme bareme1) {
        return null;
    }

    @Override
    public Object supprimer(Long id) {
        return null;
    }

    @Override
    public bareme baremeIp(Long id) {
        return null;
    }
}
