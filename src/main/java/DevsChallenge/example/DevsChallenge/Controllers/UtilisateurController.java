package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Images.Image;
import DevsChallenge.example.DevsChallenge.Images.SaveImage;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/utilisateur")
@RestController
@ToString
public class UtilisateurController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private Utilisateurservice utilisateurservice;


    @GetMapping("/afficher")
    public List<Utilisateurs> AfficherUsers(){
        log.info("Affichage de la liste des Collaborateurs ");

        return utilisateurservice.Afficher();
    }

    @GetMapping("/users")
    public List<Object[]> Afficher() {
        return utilisateurservice.userlist();

    }
    @PutMapping({"/modifier/{Id}"})
    public String ModierUser(
            @PathVariable Long Id,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String prenom,
            @RequestParam String nom,
            @Param("profile") MultipartFile profile) throws IOException {
        String ProfileNom = StringUtils.cleanPath(profile.getOriginalFilename());
        Utilisateurs utilisateuramodifier = new Utilisateurs(username,email,nom,prenom,password,profile.getOriginalFilename());
        System.out.println(utilisateuramodifier);
        if (profile != null){

            utilisateuramodifier.setProfile(SaveImage.save(profile,ProfileNom));
            log.info("Utilisateur "+utilisateuramodifier.getUsername() + " modifié avec succès");
            System.out.println(utilisateuramodifier);
            utilisateurservice.Modifier(Id, utilisateuramodifier);
        }
        return "Modification reussie avec succès";
    }

   /* @PutMapping("/modifier/{id}")
    public String modifyUser(
            @PathVariable Long id,
            @RequestBody Utilisateurs utilisateur,
            @RequestParam("profile") MultipartFile profile) throws IOException {

        String ProfileNom = StringUtils.cleanPath(profile.getOriginalFilename());

        if (profile != null) {
            utilisateur.setProfile(SaveImage.save(profile, ProfileNom));
        }

        utilisateurservice.Modifier(id, utilisateur);
        log.info("User {} modified successfully", utilisateur.getUsername());

        return "Modification successful";
    }
*/


}
