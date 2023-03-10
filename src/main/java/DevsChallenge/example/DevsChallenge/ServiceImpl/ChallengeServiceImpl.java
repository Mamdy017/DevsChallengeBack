package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.EmailConstructor;
import DevsChallenge.example.DevsChallenge.Messages.EnvoyeMailService;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.*;
import DevsChallenge.example.DevsChallenge.Repositories.ChallengeRepository;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Data
@AllArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private final ChallengeRepository challengeRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    EnvoyeMailService envoyeMailService;

    @Autowired
    EmailConstructor emailConstructor;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Message creer(Challenge challenge) throws MessagingException {
        if (challengeRepository.findByTitre(challenge.getTitre()) == null) {
            if (challenge.getDatedebut().after(new Date())) {
//                List<Utilisateurs> listutilisateurs = new ArrayList<>();
//                listutilisateurs = utilisateurRepository.findAll();
//                for(Utilisateurs u : listutilisateurs){
//                    String mon = "DevsCiwara vient de créer la challenge " + challenge.getTitre() + " en savoir plus https://chat.openai.com/auth/login";
//                    Context context = new Context();
//                    String text = templateEngine.process("newUserEmailTemplate",context);
//               //  envoyeMailService.sendEmailToMultipleRecipients("Nouvelle Challenge",mon, Arrays.asList(u.getEmail()),text);
//                }
                if ((challenge.getDatefin().after(challenge.getDatedebut())) || (challenge.getDatefin().equals(challenge.getDatedebut()))) {
                    List<Utilisateurs> listutilisateurs = new ArrayList<>();
                    listutilisateurs = utilisateurRepository.findAll();
                    for (Utilisateurs u : listutilisateurs) {
                        boolean isAdmin = false;
                        for (Roles r : u.getRoles()) {
                            if (r.getName() == Role.ROLE_ADMIN || r.getName() == Role.adminuser) {
                                isAdmin = true;
                                break;
                            }
                        }
                        if (!isAdmin) {
                            String mon = "DevsCiwara vient de créer la challenge " + challenge.getTitre() + " en savoir plus https://chat.openai.com/auth/login";
                            Context context = new Context();
                            String text = templateEngine.process("newUserEmailTemplate", context);
                            // envoyeMailService.sendEmailToMultipleRecipients("Nouvelle Challenge",mon, Arrays.asList(u.getEmail()),text);
                        }
                    }


                    this.challengeRepository.save(challenge);
                    return (Message) Message.set("Challenge creer avec succès", true);
                } else {
                    return (Message) Message.set("La date de fin ne doit pas être inferieure à la date de début", false);
                }
            } else {
                return (Message) Message.set("La date de début doit être supperieure à " +
                        "la date d'aujourd'hui ", false);
            }
        } else {
            return (Message) Message.set("Ce challenge existe déjà", false);
        }
    }


    @Transactional
    public Set<critere> getCriteriaByChallengeId(Long challengeId) {
        Challenge challenge = entityManager.find(Challenge.class, challengeId);
        return challenge.getCritere();
    }


    @Override
    public List<Challenge> afficher() {
        return challengeRepository.findAll();
    }
/*    public Message modifier(Long id, Challenge challenge) {
        try {
            Challenge ch = challengeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Challenge not found"));
            if (ch.getDatedebut().before(new Date())) {
                return Message.set("Sorry, this challenge can't be modified as the start date is before or equal to the current date", false);
            }
            ch.modifier(challenge.getTitre(), challenge.getDatedebut(), challenge.getDatefin(), challenge.getPhoto(), challenge.getDescription());
            challengeRepository.save(ch);
            return Message.set("Challenge Modified", true);
        } catch (RuntimeException e) {
            return Message.set("An error occurred", false);
        }
    }*/





/*    @Override
    public Message modifier(Long id, Challenge challenge) {
        try {
            Challenge ch = challengeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Challenge no trouvée"));
            if (ch.getDatedebut().before(new Date()))  {
                return Message.set("Désolé, cette challenge ne peut pas être modifiée car la date de début est antérieure ou égale à la date du jour",false);
            }
            ch.modifier(challenge.getTitre(),challenge.getDatedebut(),challenge.getDatefin(),challenge.getPhoto(),challenge.getDescription());
            challengeRepository.save(ch);
            System.out.println(new Date());
            return Message.set("Challenge Modifié",true);
        } catch (RuntimeException e) {
            return Message.set("une erreur s'est produite",false);
        }
    }*/


    @Override
    public String supprimer(Long id) {
        this.challengeRepository.deleteById(id);
        return "Challenge supprime avec succes";
    }


    @Override
    public Challenge ChallengeParId(Long id) {
        return challengeRepository.findById(id).get();
    }



/*
    public Message modifier(Long id, Challenge challenge) {
        Challenge challenge1 = challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        challenge1.setTitre(challenge.getTitre());
        challenge1.setDescription(challenge.getDescription());
        challenge1.setDatedebut(challenge.getDatedebut());
        challenge1.setDatefin(challenge.getDatefin());
        challenge1.setPhoto(challenge.getPhoto());

        challenge1.getCritere().clear();
        challenge1.getCate().clear();
        challenge1.getTechno().clear();

        challenge1.getCritere().addAll(challenge.getCritere());
        challenge1.getCate().addAll(challenge.getCate());
        challenge1.getTechno().addAll(challenge.getTechno());

        challengeRepository.save(challenge1);

        return Message.set("Challenge modified", true);
    }*/

    @Override
    public Message modifier(Long id, Challenge challenge) {
        Challenge challenge1 = challengeRepository.findById(id).orElse(null);
        if (challenge1 == null) {
            return (Message) Message.set("erreur", false);
        }

        // Vérifier si les champs ne sont pas nulls ou vides avant de les définir sur l'objet challenge1
        if (challenge.getTitre() != null && !challenge.getTitre().isEmpty()) {
            challenge1.setTitre(challenge.getTitre());
        }
        if (challenge.getDescription() != null && !challenge.getDescription().isEmpty()) {
            challenge1.setDescription(challenge.getDescription());
        }
        if (challenge.getDatedebut() != null) {
            challenge1.setDatedebut(challenge.getDatedebut());
        }
        if (challenge.getDatefin() != null) {
            challenge1.setDatefin(challenge.getDatefin());
        }
        if (challenge.getPhoto() != null && !challenge.getPhoto().isEmpty()) {
            challenge1.setPhoto(challenge.getPhoto());
        }
        if (challenge.getCritere() != null && !challenge.getCritere().isEmpty()) {
            challenge1.setCritere(challenge.getCritere());
        }
        if (challenge.getTechno() != null && !challenge.getTechno().isEmpty()) {
            challenge1.setTechno(challenge.getTechno());
        }
        if (challenge.getCate() != null && !challenge.getCate().isEmpty()) {
            challenge1.setCate(challenge.getCate());
        }

        challengeRepository.save(challenge1);
        return (Message) Message.set("Bon travail", true);
    }

    @Override
    public Message modifierEtat1(Long id) {
        Challenge challenge1 = challengeRepository.findById(id).orElse(null);
        if (challenge1 == null) {
            return (Message) Message.set("Une erreur est survenue", false);
        }
        String etat = challenge1.getEtat();
        if (etat.equals("Encours")) {
            return (Message) Message.set("Le challenge  "+ challenge1.getTitre() + " est en cours et peut pas être modifié", false);
        }
        if (etat.equals("Terminé")) {
            return (Message) Message.set("Le challenge "+ challenge1.getTitre() + " est terminé et peut pas être modifié", false);
        }
        challenge1.setEtat1(false);
        challengeRepository.save(challenge1);
        return (Message) Message.set("Le challenge "+ challenge1.getTitre() + " a été supprimé", true);
    }



    @Scheduled(fixedRate = 10 * 1000)
    public void updateChallengeStatus() {
        List<Challenge> challenges = challengeRepository.findAll();
        for (Challenge challenge : challenges) {
            challenge.updateChallengeStatus();
            challengeRepository.save(challenge);
        }
    }
}

