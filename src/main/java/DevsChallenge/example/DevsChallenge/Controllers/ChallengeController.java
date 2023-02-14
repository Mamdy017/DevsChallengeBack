package DevsChallenge.example.DevsChallenge.Controllers;
import DevsChallenge.example.DevsChallenge.Images.SaveImage;
import DevsChallenge.example.DevsChallenge.Messages.EnvoyeMailService;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Categories;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Models.critere;
import DevsChallenge.example.DevsChallenge.Repositories.*;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/challenge")
@RestController
@ToString
@AllArgsConstructor
@NoArgsConstructor

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
    @Autowired
    critereRepository critereRepository1;

    @Autowired
    ChallengeRepository challengeRepository;
    @ApiOperation(value = "Ajouter2 un challenge")
    @PostMapping("/ajout")
    public Object creer (
            @Param("critereids") Long[] critereids,
            @Param("tecnhoids") Long[] tecnhoids,
            @Param("cateids") Long[] cateids,
            @Param("titre") String titre,
            @Param("description") String description,
            @Param("datedebut") Date datedebut, @Param("datefin") Date datefin,
            @Param("photo")MultipartFile photo
    ) throws IOException, MessagingException {


        String photo2 = titre +photo.getOriginalFilename();
        Challenge challenge = new Challenge(titre,description,datedebut,datefin,photo2);

        challenge.setPhoto(SaveImage.save(photo,photo2,"challenge"));
        for (Long critereid : critereids) {
            challenge.getCritere().add(critereRepository1.findById(critereid).get());
        }
        for (Long cateid : cateids) {
            challenge.getCate().add(categorieRepository.findById(cateid).get());
        }
        for (Long technoid : tecnhoids) {
            challenge.getTechno().add(technoRepository.findById(technoid).get());
        }
        return challengeService.creer(challenge);
    }



    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Challenge> list(){
        return challengeService.afficher();
    }


    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher/{id}")
    public Challenge list1(@PathVariable Long id ){
        Challenge challenge = challengeService.ChallengeParId(id);
        return challenge != null ? challenge : null;
    }

    @GetMapping("/criteria/{challengeId}")
    public
    ResponseEntity<Set<critere>> getCriteriaByChallengeId(@PathVariable Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);
        if (challenge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Set<critere> criteria = challenge.getCritere();
        return new ResponseEntity<>(criteria, HttpStatus.OK);
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
        filteredChallenges.forEach(challenge -> challenge.updateChallengeStatus());
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
        filteredChallenges.forEach(challenge -> {
            challenge.updateChallengeStatus();
            challengeRepository.save(challenge);
        });        return filteredChallenges;
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
        filteredChallenges.forEach(challenge -> {
            challenge.updateChallengeStatus();
            challengeRepository.save(challenge);
        });

        return filteredChallenges;
    }

    @GetMapping("/decroissant")
    public List<Challenge> decroissant(){
        List<Challenge> challenges = challengeService.afficher();
        challenges.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
        challenges.forEach(challenge -> {
            challenge.updateChallengeStatus();
            challengeRepository.save(challenge);
        });        return challenges.subList(0, Math.min(challenges.size(), 8));
    }

/*        @PutMapping("/{id}")
    public Message updateCategory(@PathVariable Long id, @RequestBody Challenge challenge) {
        return challengeService.modifier(id,challenge);
    }*/
@PutMapping("/modification/{id}")
public Message modifyChallenge(
        @PathVariable Long id,
        @RequestParam("critereIds") Long[] critereIds,
        @RequestParam("tecnhoIds") Long[] tecnhoIds,
        @RequestParam("cateIds") Long[] cateIds,
        @RequestParam("titre") String titre,
        @RequestParam("description") String description,
        @RequestParam("datedebut") Date datedebut,
        @RequestParam("datefin") Date datefin,
        @RequestParam("photo") MultipartFile photo
) {
    Challenge challenge = challengeService.ChallengeParId(id);
    if (challenge == null) {
        return Message.set("Challenge not found", false);
    }
    if (challenge.getDatedebut().before(new Date())) {
        return Message.set("Desolé cet challenge ne peut pas être modifié", false);
    }
    challenge.setTitre(titre);
    challenge.setDescription(description);
    challenge.setDatedebut(datedebut);
    challenge.setDatefin(datefin);
    String photoName = titre + photo.getOriginalFilename();
    challenge.setPhoto(photoName);
    try {
        challenge.setPhoto(SaveImage.save(photo, photoName, "challenge"));
    } catch (Exception e) {
        return Message.set("An error occurred while saving the image", false);
    }

    Set<critere> criteria = new HashSet<>();
    for (Long critereId : critereIds) {
        critere crit = critereRepository1.findById(critereId).orElse(null);
        if (crit != null) {
            criteria.add(crit);
        }
    }
    challenge.setCritere(criteria);

    Set<Categories> categories = new HashSet<>();
    for (Long cateId : cateIds) {
        Categories cat = categorieRepository.findById(cateId).orElse(null);
        if (cat != null) {
            categories.add(cat);
        }
    }
    challenge.setCate(categories);

    Set<Technologies> technologies = new HashSet<>();
    for (Long technhoId : tecnhoIds) {
        Technologies tech = technoRepository.findById(technhoId).orElse(null);
        if (tech != null) {
            technologies.add(tech);
        }
    }
    challenge.setTechno(technologies);

    challengeService.modifier(id, challenge);
    return Message.set("Challenge modified successfully", true);
}
    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return challengeService.supprimer(Id);
    }

    @GetMapping("/challenges")
    public List<Challenge> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        challenges.forEach(challenge -> challenge.updateChallengeStatus());
        return challenges;
    }
}
