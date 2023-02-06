package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Appreciation;
import DevsChallenge.example.DevsChallenge.Repositories.AppreciationRepository;
import DevsChallenge.example.DevsChallenge.Services.ApprecitionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApprectionServiceImpl implements ApprecitionService {

    private final AppreciationRepository appreciationRepository;
    @Override
    public Object creer(Appreciation appreciation) {
        this.appreciationRepository.save(appreciation);
        return Message.set("Commentaire apprécié",true);
    }

    @Override
    public List<Appreciation> afficher() {
        return this.appreciationRepository.findAll();
    }

    @Override
    public Message modifier(Long id, Appreciation appreciation) {
        return this.appreciationRepository.findById(id).map(app->{
                app.setType(appreciation.getType());
                this.appreciationRepository.save(app);
                return Message.set("Apprecition modifiée",true);
        }).orElseThrow(()->new RuntimeException(String.valueOf(Message.set("Une erreur s'est produite",false))));
    }

    @Override
    public Object supprimer(Long id) {
        this.appreciationRepository.deleteById(id);
        return Message.set("Apprecition supprimée avec succès",true);
    }

    @Override
    public Appreciation trouverAppParId(Long id) {
        return appreciationRepository.findById(id).get();
    }
}
