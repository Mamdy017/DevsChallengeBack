package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.EnvoyeMailService;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/challenge")
@RestController
@ToString
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    EnvoyeMailService envoyeMailService;

    @ApiOperation(value = "Ajouter2 un challenge")
    @PostMapping("/ajout")
    public Object creer (@RequestBody Challenge challenge){

        try {
            List<Utilisateurs> listutilisateurs = new ArrayList<>();
            List<String> emails = new ArrayList<>();
            listutilisateurs = utilisateurRepository.findAll();
            for(Utilisateurs u : listutilisateurs){
                emails.add(u.getEmail());
            }
            String mon = "DevsCiwara vient de créer la challenge " + challenge.getTitre() + " en savoir plus  https://chat.openai.com/auth/login"
                    ;
                    envoyeMailService.sendEmailToMultipleRecipients("Nouvelle Challenge",mon,emails);

            return challengeService.creer(challenge);

        } catch (Exception e) {
            return Message.set(" la techno " + challenge.getTitre() + " existe déjà", true);
        }
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Challenge> list(){
        return challengeService.afficher();
    }

    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Challenge Modifier(@PathVariable Long Id, Challenge challenge) {
        return challengeService.modifier(Id, challenge);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return challengeService.supprimer(Id);
    }

}
