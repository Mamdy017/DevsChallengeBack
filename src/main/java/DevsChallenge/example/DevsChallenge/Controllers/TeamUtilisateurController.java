package DevsChallenge.example.DevsChallenge.Controllers;


import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.*;
import DevsChallenge.example.DevsChallenge.Repositories.ChallengeRepository;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import DevsChallenge.example.DevsChallenge.Repositories.TeamUtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import DevsChallenge.example.DevsChallenge.Services.TeamService;
import DevsChallenge.example.DevsChallenge.Services.TeamUtilisateursService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.*;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/teamusrs")
@RestController
@ToString
public class TeamUtilisateurController {
    @Autowired
    TeamUtilisateursService teamUtilisateursService;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    TeamUtilisateurRepository teamUtilisateurRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ChallengeService challengeService;
    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    TeamService teamService;

    @PostMapping("/ajout/{teamid}")
    public Object creer(@PathVariable("teamid") Long teamid, @RequestBody TeamUtilisateurs teamUtilisateurs) {

        try {
            teamUtilisateurs.getTeam().add(teamRepository.findById(teamid).get());
            return teamUtilisateursService.creer(teamUtilisateurs);
        } catch (Exception e) {
            return Message.set(" ce utilisateurs  existe d??j??", true);
        }
    }


    @GetMapping("/afficher")
    public List<TeamUtilisateurs> list() {
        return teamUtilisateursService.afficher();
    }

    @GetMapping("/afficher2")
    public List<Utilisateurs> list2() {
        List<Utilisateurs> listutilisateurs = new ArrayList<>();
        listutilisateurs = utilisateurRepository.findAll();
        List<Utilisateurs> listNonAdminUsers = new ArrayList<>();
        for (Utilisateurs u : listutilisateurs) {
            boolean isAdmin = false;
            for (Roles r : u.getRoles()) {
                if (r.getName() == Role.ROLE_ADMIN) {
                    isAdmin = true;
                    break;
                }
            }
            if (!isAdmin) {
                listNonAdminUsers.add(u);
            }
        }
        return listNonAdminUsers;
    }


    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Message Modifier(@PathVariable Long Id, TeamUtilisateurs teamUtilisateurs) {
        return teamUtilisateursService.modifier(Id, teamUtilisateurs);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public Object Supprimer(@PathVariable Long Id) {

        return teamUtilisateursService.supprimer(Id);
    }

    @GetMapping("/afficherEquipeMembre/{challengeId}/{teamId}")
    public List<Utilisateurs> getTeamUsersByChallengeAndTeam(@PathVariable("challengeId") Long challengeId,
                                                             @PathVariable("teamId") Long teamId) {
        Challenge challenge = new Challenge();
        challenge.setId(challengeId);
        Team team = new Team();
        team.setId(teamId);
        List<TeamUtilisateurs> teamUtilisateurs = teamUtilisateurRepository.findByChallengeAndTeam(challenge, team);
        List<Utilisateurs> utilisateurs = new ArrayList<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            if (tu.getType() == 1 || tu.getType() == 2) {
                utilisateurs.add(tu.getUtilisateurs());
            }
        }
        return utilisateurs;
    }

    @PostMapping("/teams/{id}/{challengeId}")
    public Object addTeamUsersToTeamForChallenge(@Param("userIds") Long[] userIds, @PathVariable Long id, @PathVariable Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElse(null);
        if (challenge == null) {
            return Message.set("Cet challenge n'existe pas", false);
        }
        Team team = teamRepository.findById(id).get();
        for (Long userId : userIds) {
            Utilisateurs utilisateurs = utilisateurRepository.findById(userId).orElse(null);
            if (utilisateurs == null) {
                return Message.set("Cet utilisateur n'existe pas", false);
            }
            Optional<TeamUtilisateurs> existingTeamUserForType1 = teamUtilisateurRepository.findByUtilisateursAndTypeAndChallenge(utilisateurs, 1, challenge);
            Optional<TeamUtilisateurs> existingTeamUserForType2 = teamUtilisateurRepository.findByUtilisateursAndTypeAndChallenge(utilisateurs, 2, challenge);
            if (existingTeamUserForType1.isPresent()) {
                return Message.set("Cet utilisateur est d??j?? dans une ??quipe", false);
            }
            if (existingTeamUserForType2.isPresent()) {
                return Message.set("Cet utilisateur est d??j?? dans une ??quipe", false);
            }
            TeamUtilisateurs teamUtilisateurs = new TeamUtilisateurs();
            teamUtilisateurs.setType(0);
            teamUtilisateurs.setUtilisateurs(utilisateurs);
            teamUtilisateurs.setChallenge(challenge);
            teamUtilisateurs.getTeam().add(team);
            teamUtilisateurRepository.save(teamUtilisateurs);
        }
        return Message.set("Demande envoyer aux utilisteurs !!", true);


    }

    @GetMapping("/afficherEquipeParUtilisateur/{challengeId}/{userId}")
    public Set<Team> getTeamsByChallengeAndUser(@PathVariable Long challengeId, @PathVariable Long userId) {
        Challenge challenge = new Challenge();
        challenge.setId(challengeId);
        Utilisateurs user = new Utilisateurs();
        user.setId(userId);
        Set<TeamUtilisateurs> teamUtilisateurs = teamUtilisateurRepository.findByChallengeAndUtilisateurs(challenge, user);
        Set<Team> teams = new HashSet<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            teams.addAll(tu.getTeam());
        }
        return teams;
    }


    @PutMapping("/{id}/{userId}/{challengeId}")
    public Message updateTypeAndRemoveZero(@PathVariable Long id, @PathVariable Long userId, @PathVariable Long challengeId) {
        Utilisateurs utilisateur = entityManager.find(Utilisateurs.class, userId);
        Challenge challenge = entityManager.find(Challenge.class, challengeId);
        return this.teamUtilisateursService.updateTypeAndRemoveZero(id, utilisateur, challenge);
    }



    @GetMapping("/user/{userId}")
    public List<TeamUtilisateurs> getTeamsByUser(@PathVariable Long userId) {
        Utilisateurs user = entityManager.find(Utilisateurs.class, userId);
        return teamUtilisateursService.findTeamsByUser(user);
    }

    @GetMapping("/teamUserId/{userId}")
    public TeamUtilisateurs ParId(@PathVariable Long userId) {
        return teamUtilisateursService.trouverTeamParId(userId);
    }
}
