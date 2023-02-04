package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.critereRepository;
import DevsChallenge.example.DevsChallenge.Services.critereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class critereServiceImpl implements critereService {
    @Autowired
    critereRepository critereRepository1;

    @Override
    public Message creer(critere critere1) {
        this.critereRepository1.save(critere1);
        return Message.set("Critère ajouter avec succès",true);
    }
    @Override
    public List<critere> afficher() {
        return critereRepository1.findAll();
    }

    @Override
    public critere modifier(Long id, critere critere1) {
        return null;
    }

    @Override
    public Object supprimer(Long id) {
        return null;
    }

    @Override
    public critere critereIp(Long id) {
        return null;
    }


}
