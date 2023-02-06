package DevsChallenge.example.DevsChallenge.Services;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Correction;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.critere;

import java.util.List;

public interface CorrectionService {


    Message addEtats(List<String> etats, Solution solution, List<critere> criteres);

    List<Correction> afficher();
}
