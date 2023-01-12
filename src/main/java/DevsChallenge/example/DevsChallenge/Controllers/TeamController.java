package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Services.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
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



}
