package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Appreciation;
import DevsChallenge.example.DevsChallenge.Models.Commentaire;
import DevsChallenge.example.DevsChallenge.Models.Question;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.CommentaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/commentaire")
@RestController
@ToString
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;

    @ApiOperation(value = "Ajouter2 un pays")
    @PostMapping("/ajout/{userdid}/{questionid}")
    public Object creer (@PathVariable("userdid") Long userdid, @PathVariable("questionid") Long questionid, @RequestBody Commentaire commentaire){

        try {
            commentaire.setQuestion(new Question(questionid));
            commentaire.setUtilisateurs(new Utilisateurs(userdid));
            return commentaireService.creer(commentaire);
        } catch (Exception e) {
            return Message.set(" Cet commentaire " + commentaire.getDescription() + " existe déjà",  true);
        }
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Commentaire> list(){
        return commentaireService.afficher();
    }

    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Commentaire Modifier(@PathVariable Long Id, Commentaire commentaire) {
        return commentaireService.modifier(Id, commentaire);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return commentaireService.supprimer(Id);
    }

    @ApiOperation(value = "Afficher question")
    @GetMapping("/afficher/{questionId}")
    public List<Commentaire> listparidC(@PathVariable Long questionId){
        return commentaireService.findByQuestionId(questionId);
    }

    @PostMapping("/incrementType1")
    public ResponseEntity<Commentaire> incrementType1(@RequestParam Long questionid) {
        Commentaire commentaire = commentaireService.incrementType1(questionid);
        return ResponseEntity.ok(commentaire);
    }

    @PostMapping("/incrementType2")
    public ResponseEntity<Commentaire> incrementType2(@RequestParam Long questionid) {
        Commentaire commentaire = commentaireService.incrementType2(questionid);
        return ResponseEntity.ok(commentaire);
    }
}
