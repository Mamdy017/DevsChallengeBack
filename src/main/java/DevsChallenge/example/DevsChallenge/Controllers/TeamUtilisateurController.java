package DevsChallenge.example.DevsChallenge.Controllers;


import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import DevsChallenge.example.DevsChallenge.Services.TeamUtilisateursService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/teamusrs")
@RestController
@ToString
public class TeamUtilisateurController {
    @Autowired
    TeamUtilisateursService teamUtilisateursService;


    @Autowired
    TeamRepository teamRepository;
    @PostMapping("/ajout/{teamid}")
    public Object creer (@PathVariable("teamid") Long teamid,  @RequestBody TeamUtilisateurs teamUtilisateurs){

        try {
            teamUtilisateurs.getTeam().add(teamRepository.findById(teamid).get());
            return teamUtilisateursService.creer(teamUtilisateurs);
        } catch (Exception e) {
            return Message.set(" ce utilisateurs  existe déjà",  true);
        }
    }

    @GetMapping("/afficher")
    public List<TeamUtilisateurs> list(){
        return teamUtilisateursService.afficher();
    }



    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Message Modifier(@PathVariable Long Id, TeamUtilisateurs teamUtilisateurs) {
        return teamUtilisateursService.modifier(Id,teamUtilisateurs);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public Object Supprimer(@PathVariable Long Id) {

        return teamUtilisateursService.supprimer(Id);
    }
}
