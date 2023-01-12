package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Question;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import DevsChallenge.example.DevsChallenge.Services.QuestionService;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "devsCiwara", description = "azertyuio")
@RequestMapping("/devs/auth/question")
@RestController
@ToString
@AllArgsConstructor
public class QuestionContrller {


    @Autowired
    QuestionService questionService;
    @Autowired
    Utilisateurservice utilisateurservice;
    @Autowired
    ChallengeService challengeService;

    @ApiOperation(value = "Ajouter une question")
    @PostMapping("/ajout/{challengeId}/{userid}")
    public Object creer (@PathVariable("idchallenge") Long challengeId,@PathVariable("userid") Long userid, @RequestBody Question question){


            Challenge ch = challengeService.ChallengeParId(challengeId);
            Utilisateurs user =utilisateurservice.userParId(userid);
            Question Q = new Question(question);
            Q.setChallenge(ch);
            Q.setUtilisateurs(user);

            return questionService.creer(Q);

    }

    @ApiOperation(value = "Afficher question")
    @GetMapping("/afficher")
    public List<Question> list(){
        return questionService.afficher();
    }

    @ApiOperation(value = "Modifier question")
    @PutMapping("/modifier/{Id}")
    public Question Modifier(@PathVariable Long Id, Question question) {
        return questionService.modifier(Id, question);
    }

    @ApiOperation(value = "Supprimer question")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return questionService.supprimer(Id);
    }

}
