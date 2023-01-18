package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Images.SaveImage;
import DevsChallenge.example.DevsChallenge.Messages.EnvoyeMailService;
import DevsChallenge.example.DevsChallenge.Messages.Message;

import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;

import DevsChallenge.example.DevsChallenge.Repositories.CategorieRepository;
import DevsChallenge.example.DevsChallenge.Repositories.TechnoRepository;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    TechnoRepository technoRepository;

    @ApiOperation(value = "Ajouter2 un challenge")
    @PostMapping("/ajout/{idcate}/{idtech}")
   public Object creer (
            @PathVariable ("idcate") Long idcate, @PathVariable("idtech") Long idtech,
            @Param("titre") String titre, @Param("critere1") String critere1,
            @Param("critere2") String critere2, @Param("critere3") String critere3,
            @Param("critere4") String critere4, @Param("description") String description,
            @Param("datedebut") Date datedebut, @Param("datefin") Date datefin,
            @Param("photo")MultipartFile photo
            ) throws IOException, MessagingException {

        String photo2 = titre +photo.getOriginalFilename();
            Challenge challenge = new Challenge(titre,critere1,critere2,critere3,critere4,description,datedebut,datefin,photo2);
            challenge.setCategories(categorieRepository.findById(idcate).get());
            challenge.setTechnologies(technoRepository.findById(idtech).get());
            challenge.setPhoto(SaveImage.save(photo,photo2,"challenge"));

        return challengeService.creer(challenge);
    }



    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Challenge> list(){
        return challengeService.afficher();
    }



    @GetMapping("/encours")
    public List<Challenge> encours(){
        List<Challenge> allChallenges = challengeService.afficher();
        List<Challenge> filteredChallenges = new ArrayList<>();
        Date today = new Date();
        for (Challenge challenge : allChallenges) {
            if (challenge.getDatedebut().before(today) && challenge.getDatefin().after(today)) {
                filteredChallenges.add(challenge);
            }
        }
        return filteredChallenges;
    }

    @GetMapping("/terminer")
    public List<Challenge> terminer(){
        List<Challenge> allChallenges = challengeService.afficher();
        List<Challenge> filteredChallenges = new ArrayList<>();
        Date today = new Date();
        for (Challenge challenge : allChallenges) {
            if (challenge.getDatedebut().before(today) && challenge.getDatefin().before(today)) {
                filteredChallenges.add(challenge);
            }
        }
        return filteredChallenges;
    }

    @GetMapping("/avenir")
    public List<Challenge> avenir(){
        List<Challenge> allChallenges = challengeService.afficher();
        List<Challenge> filteredChallenges = new ArrayList<>();
        Date today = new Date();
        for (Challenge challenge : allChallenges) {
            if (challenge.getDatedebut().after(today) && challenge.getDatefin().after(today)) {
                filteredChallenges.add(challenge);
            }
        }
        return filteredChallenges;
    }

    @GetMapping("/decroissant")
    public List<Challenge> decroissant(){
        List<Challenge> challenges = challengeService.afficher();
        challenges.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
        return challenges.subList(0, Math.min(challenges.size(), 8));
    }

        @PutMapping("/{id}")
    public Message updateCategory(@PathVariable Long id, @RequestBody Challenge challenge) {
        return challengeService.modifier(id,challenge);
    }
    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return challengeService.supprimer(Id);
    }

}
