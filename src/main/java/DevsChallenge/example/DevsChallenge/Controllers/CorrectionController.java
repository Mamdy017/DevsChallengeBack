package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Correction;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Services.CorrectionService;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/correction")
@RestController
@ToString
public class CorrectionController {
    @Autowired
    CorrectionService correctionService;


    @PostMapping
    public Message addEtats(@RequestParam List<String> etats,
                            @RequestParam Long solutionId,
                            @RequestParam List<Long> critereIds) {
        Solution solution = new Solution();
        solution.setId(solutionId);
        List<critere> criteres = new ArrayList<>();
        for (Long critereId : critereIds) {
            critere critere = new critere();
            critere.setId(critereId);
            criteres.add(critere);
        }
        return this.correctionService.addEtats(etats, solution, criteres);
    }

    @GetMapping("/afficher")
    public List<Correction> list(){
        return correctionService.afficher();
    }

}
