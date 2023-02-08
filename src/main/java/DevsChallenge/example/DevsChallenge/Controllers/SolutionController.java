package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Images.SaveImage;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Solution;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/solution")
@RestController
@ToString
public class SolutionController {
    @Autowired
    SolutionService solutionService;
   @ApiOperation(value = "Ajouter2 un une solution")
   @PostMapping("/ajout/{challengeid}/{teamid}/{idusers}")
   public Object creer(@PathVariable("challengeid") Challenge challengeid,
                       @PathVariable("teamid") Team teamid,
                       @PathVariable("idusers") Utilisateurs idusers,
                       @RequestParam(value = "lienGithub", required = false) String lienGithub,
                       @RequestParam(value = "source", required = false) MultipartFile source,
                       @RequestParam(value = "point", required = false) String point) throws IOException, MessagingException {
       Solution solution = new Solution(lienGithub, point, "");
       if (source != null) {
           String photo2 = teamid.getNom() + source.getOriginalFilename();
           solution.setSource(SaveImage.save(source, photo2, "solution"));
       }
       solution.setChallenge(challengeid);
       solution.setTeam(teamid);
       return solutionService.creer(solution, challengeid, teamid, idusers);
   }





    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Solution> list(){
        return solutionService.afficher();
    }


    @GetMapping("/challenges/{challengeId}/solutions")
    public List<Solution> getSolutionsByChallengeId(@PathVariable Long challengeId) {
        return solutionService.findByChallengeId(challengeId);
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


    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<Solution>> getSolutionsByChallengeIdP(@PathVariable Long challengeId) {
        List<Solution> solutions = solutionService.getSolutionsByChallengeId(challengeId);
        return ResponseEntity.ok(solutions);
    }
}








/*


*/
