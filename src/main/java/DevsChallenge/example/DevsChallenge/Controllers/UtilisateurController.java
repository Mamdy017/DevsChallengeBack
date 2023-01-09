package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/utilisateur")
@RestController
public class UtilisateurController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private Utilisateurservice utilisateurservice;


    @GetMapping("/afficher")
    public List<Utilisateurs> AfficherUsers(){
        log.info("Affichage de la liste des Collaborateurs ");

        return utilisateurservice.Afficher();
    }


 /*   @PutMapping({"/modifier"})
    public String ModierUser(@RequestBody Utilisateurs users){
        log.info("Collaborateur "+users.getUsername() + " modifié avec succès");
        utilisateurservice.Modifier(users);
        return "Modification reussie avec succès";
    }*/

    @PutMapping({"/modifier"})
    public String ModierUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String profile){
        Utilisateurs utilisateuramodifier = new Utilisateurs(username,email,nom,prenom,password,profile);

        log.info("Collaborateur "+utilisateuramodifier.getUsername() + " modifié avec succès");
        utilisateurservice.Modifier(utilisateuramodifier);
        return "Modification reussie avec succès";
    }


}
