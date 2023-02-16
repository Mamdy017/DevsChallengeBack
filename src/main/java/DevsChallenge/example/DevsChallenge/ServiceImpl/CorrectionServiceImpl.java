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

import java.io.Console;
import java.util.Collections;
import java.util.List;

@Service
public class CorrectionServiceImpl implements CorrectionService {

    @Autowired
    CorrectionRepository correctionRepository;

    @Autowired
    SolutionRepository solutionRepository;

/*    public Message addEtats(List<String> etats, Solution solution, List<critere> criteres) {
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
            currentSolution.setTotal(100000);
            this.correctionRepository.save(correction);
            solutionRepository.save(currentSolution);
        }

        return Message.set("cool",true);
    }*/

    public Message addEtats(List<String> etats, Solution solution, List<critere> criteres) {
        if (etats.size() != criteres.size()) {
            return Message.set("Le nombre d'états et de critères doit être égal", false);
        }

        Solution currentSolution = solutionRepository.findById(solution.getId()).orElse(null);
        if (currentSolution == null) {
            return Message.set("La solution avec l'ID spécifié n'existe pas", false);
        }

        if (currentSolution.getEtat().equals("1")) {
            return Message.set("Cette solution a déjà été corrigée", false);
        }

        int total = 0;
        System.out.println(criteres);
        for (int i = 0; i < criteres.size(); i++) {
            critere critere = new critere();
            critere = criteres.get(i);
            String etat = etats.get(i);
System.out.println("mon etat"+criteres.get(i).getBaremes());
            int bareme = 0;
            if (etat.equals("valide")) {
                bareme = critere.getBaremes();
                System.out.println("mon bareme"+ bareme);
            } else if (etat.equals("partiel")) {
                bareme = critere.getBaremes() / 2;
            }

            total += bareme;
            Correction correction = new Correction();
            correction.setEtat(etat);
            correction.setSolution(solution);
            correction.setCritere(Collections.singleton(critere));
            correctionRepository.save(correction);
        }
        System.out.println(total);

        currentSolution.setEtat("1");
        currentSolution.setTotal(total);
        solutionRepository.save(currentSolution);

        return Message.set("Les états ont été ajoutés avec succès", true);
    }

    @Override
    public List<Correction> afficher() {
        return correctionRepository.findAll();
    }




    // ==========================================================================================================
}
