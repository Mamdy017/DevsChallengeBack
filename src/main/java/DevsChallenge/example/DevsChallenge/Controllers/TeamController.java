package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import DevsChallenge.example.DevsChallenge.Services.TeamService;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/team")
@RestController
@ToString
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    Utilisateurservice utilisateurservice;

    @Autowired
    ChallengeService challengeService;
    @ApiOperation(value = "Ajouter2 un pays")
    @PostMapping("/ajout")
    public Object creer (@RequestBody Team team){

        try {
            return teamService.creer(team);
        } catch (Exception e) {
            return Message.set(" categories " + team.getNom() + " existe déjà",  true);
        }
    }

    @GetMapping("/afficher")
    public List<Team> list(){
        return teamService.afficher();
    }



    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Team Modifier(@PathVariable Long Id, Team team) {
        return teamService.modifier(Id,team);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public Object Supprimer(@PathVariable Long Id) {

        return teamService.supprimer(Id);
    }

    @PostMapping("/teams/{creatorId}/{challengeId}")
    public Message createTeam(@RequestBody Team team, @PathVariable Long creatorId, @PathVariable Long challengeId) {
        Utilisateurs creator = utilisateurservice.userParId(creatorId);
        Challenge challenge = challengeService.ChallengeParId(challengeId);
        return this.teamService.createTeamAndAddCreator(team.getNom(), creator, challenge);
    }



}
