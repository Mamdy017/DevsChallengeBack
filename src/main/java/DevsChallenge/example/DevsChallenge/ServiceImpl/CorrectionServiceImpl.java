package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Correction;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.bareme;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.CorrectionRepository;
import DevsChallenge.example.DevsChallenge.Repositories.SolutionRepository;
import DevsChallenge.example.DevsChallenge.Services.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    @Autowired
    CorrectionRepository correctionRepository;

    @Autowired
    SolutionRepository solutionRepository;

    /*public Message addEtats(List<String> etats, Solution solution, List<critere> criteres) {
        if (etats.size() != criteres.size()) {
            return Message.set("Le nombre d'états et de critères doit être égal",false);
        }
        Solution currentSolution = solutionRepository.findById(solution.getId()).orElse(null);
        if (currentSolution == null) {
           Message.set("La solution avec l'ID spécifié n'existe pas",false);
        }
        if (currentSolution.getEtat().equals("1")) {
            return Message.set("Cette solution a été déjà corriger",false);
        }
        for (int i = 0; i < criteres.size(); i++) {
            Correction correction = new Correction();
            correction.setEtat(etats.get(i));
            correction.setSolution(solution);
            correction.setCritere(Collections.singleton(criteres.get(i)));
            currentSolution.setEtat("1");
            this.correctionRepository.save(correction);
            solutionRepository.save(currentSolution);
        }

        return Message.set("cool",true);
    }
*/
    public Message addEtats(List<String> etats, Solution solution, List<critere> criteres) {
        if (etats.size() != criteres.size()) {
            return Message.set("Le nombre d'états et de critères doit être égal",false);
        }
        Solution currentSolution = solutionRepository.findById(solution.getId()).orElse(null);
        if (currentSolution == null) {
            Message.set("La solution avec l'ID spécifié n'existe pas",false);
        }
        if (currentSolution.getEtat().equals("1")) {
            return Message.set("Cette solution a été déjà corriger",false);
        }

        int total = 0;
        for (int i = 0; i < criteres.size(); i++) {
            Correction correction = new Correction();
            correction.setEtat(etats.get(i));
            correction.setSolution(solution);
            correction.setCritere(Collections.singleton(criteres.get(i)));
            this.correctionRepository.save(correction);

            bareme Bareme = (bareme) criteres.get(i).getBaremes();
            if (etats.get(i).equals("valide")) {
                total += Bareme.getBareme();
            } else if (etats.get(i).equals("partiel")) {
                total += Bareme.getBareme() / 2;
            } else if (etats.get(i).equals("non-valide")) {
                total += 0;
            }
        }
        currentSolution.setTotal(total);
        currentSolution.setEtat("1");
        solutionRepository.save(currentSolution);

        return Message.set("cool",true);
    }


    @Override
    public List<Correction> afficher() {
        return correctionRepository.findAll();
    }




    // ==========================================================================================================
}
