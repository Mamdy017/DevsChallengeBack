package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Team;
import DevsChallenge.example.DevsChallenge.Models.TeamUtilisateurs;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.TeamRepository;
import DevsChallenge.example.DevsChallenge.Repositories.TeamUtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.TeamUtilisateursService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TeamUtilisateursServiceImpl implements TeamUtilisateursService {
    private final TeamUtilisateurRepository teamUtilisateurRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;
    private final TeamRepository teamRepository;

    @Override
    public Object creer(TeamUtilisateurs teamUtilisateurs) {
        this.teamUtilisateurRepository.save(teamUtilisateurs);
        return Message.set("Demande d'ajoout au team envoyée avec succès", true);
    }


    @Override
    public List<TeamUtilisateurs> afficher() {
        return teamUtilisateurRepository.findAll();
    }

    @Override
    public Message modifier(Long id, TeamUtilisateurs teamUtilisateurs) {
        return this.teamUtilisateurRepository.findById(id).map(tu -> {
            tu.setType(teamUtilisateurs.getType());
            this.teamUtilisateurRepository.save(tu);
            return Message.set(" Demande confirmée", true);
        }).orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Demande non trouvée", true))));
    }

    @Override
    public Object supprimer(Long id) {
        this.teamUtilisateurRepository.deleteById(id);
        return Message.set("demande annulé avec succes", true);
    }

    @Override
    public TeamUtilisateurs trouverTeamParId(Long id) {
        return teamUtilisateurRepository.findById(id).get();
    }

    @Override
    public List<Utilisateurs> Afficher2() {
        return utilisateurRepository.findAll();
    }

    public void addTeamUtilisateurToTeam(Utilisateurs utilisateurs, String teamName) {
        // Create a new TeamUtilisateurs object
        TeamUtilisateurs teamUtilisateurs = new TeamUtilisateurs();
        teamUtilisateurs.setType(0);
        teamUtilisateurs.setUtilisateurs(utilisateurs);

        // Retrieve the desired Team object from the database
        Team team = teamRepository.findByNom(teamName);

        // Add the TeamUtilisateurs object to the Team's set of users
        teamUtilisateurs.getTeam().add(team);

        // Save the updated Team object to the database
        teamRepository.save(team);
    }


    public List<Utilisateurs> findByChallengeAndTeam(Challenge challenge, Team team) {
        List<TeamUtilisateurs> teamUtilisateurs = new ArrayList<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            if (tu.getChallenge().equals(challenge) && tu.getTeam().contains(team)) {
                teamUtilisateurs.add(tu);
            }
        }
        List<Utilisateurs> utilisateurs = new ArrayList<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            utilisateurs.add(tu.getUtilisateurs());
        }
        return utilisateurs;
    }


    public Set<Team> getTeamsByChallengeAndUser(Challenge challenge, Utilisateurs user) {
        Set<TeamUtilisateurs> teamUtilisateurs = new HashSet<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            if (tu.getChallenge().equals(challenge) && tu.getUtilisateurs().equals(user)) {
                teamUtilisateurs.add(tu);
            }
        }
        Set<Team> teams = new HashSet<>();
        for (TeamUtilisateurs tu : teamUtilisateurs) {
            teams.addAll(tu.getTeam());
        }
        return teams;
    }

    @Transactional
    public Message updateTypeAndRemoveZero(Long id, Utilisateurs utilisateur, Challenge challenge) {
        TeamUtilisateurs teamUtilisateur = entityManager.find(TeamUtilisateurs.class, id);
        if (teamUtilisateur != null && teamUtilisateur.getUtilisateurs().equals(utilisateur) &&
                teamUtilisateur.getChallenge().equals(challenge)) {
            teamUtilisateur.setType(2);
            entityManager.persist(teamUtilisateur);
        }

        entityManager.createQuery("DELETE FROM TeamUtilisateurs t WHERE t.utilisateurs = :utilisateur AND t.challenge = :challenge AND t.type = 0")
                .setParameter("utilisateur", utilisateur)
                .setParameter("challenge", challenge)
                .executeUpdate();

        return Message.set("Demande accepte avec succes",true);
    }





    @Override

    public List<TeamUtilisateurs> findTeamsByUser(Utilisateurs user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeamUtilisateurs> query = cb.createQuery(TeamUtilisateurs.class);
        Root<TeamUtilisateurs> teamUtilisateursRoot = query.from(TeamUtilisateurs.class);

        query.select(teamUtilisateursRoot).where(cb.equal(teamUtilisateursRoot.get("utilisateurs"), user), cb.equal(teamUtilisateursRoot.get("type"), 0));

        return entityManager.createQuery(query).getResultList();
    }
}
