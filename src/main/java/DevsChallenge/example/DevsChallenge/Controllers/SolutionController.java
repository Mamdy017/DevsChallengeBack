package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/solution")
@RestController
@ToString
public class SolutionController {


    @Autowired
    SolutionService solutionService;

    @ApiOperation(value = "Ajouter2 un pays")
    @PostMapping("/ajout/{userdid}/{challengeid}")
    public Object creer (@RequestBody Solution solution,@PathVariable("userdid") Utilisateurs userdid , @PathVariable("challengeid") Challenge challengeid  ){
        solution.setUtilisateurs(userdid);
        solution.setChallenge(challengeid);
            return solutionService.creer(solution,userdid,challengeid);
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Solution> list(){
        return solutionService.afficher();
    }

    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Solution Modifier(@PathVariable Long Id, Solution solution) {
        return solutionService.modifier(Id, solution);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return solutionService.supprimer(Id);
    }
}








/*


*/
