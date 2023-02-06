package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Correction;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.CorrectionRepository;
import DevsChallenge.example.DevsChallenge.Services.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    @Autowired
    CorrectionRepository correctionRepository;


    public Message addEtats(List<String> etats, Solution solution, List<critere> criteres) {
        if (etats.size() != criteres.size()) {
            throw new IllegalArgumentException("Le nombre d'états et de critères doit être égal");
        }
        for (int i = 0; i < criteres.size(); i++) {
            Correction correction = new Correction();
            correction.setEtat(etats.get(i));
            correction.setSolution(solution);
            correction.setCritere(Collections.singleton(criteres.get(i)));
            this.correctionRepository.save(correction);
        }
        return Message.set("cool",true);
    }



}
