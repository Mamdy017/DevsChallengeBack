package DevsChallenge.example.DevsChallenge.ServiceImpl;

import DevsChallenge.example.DevsChallenge.Messages.EnvoyeMailService;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Challenge;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.ChallengeRepository;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.ChallengeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {
   @Autowired
    private final ChallengeRepository challengeRepository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    EnvoyeMailService envoyeMailService;
    @Override
    public Message creer(Challenge challenge) {

        if(challenge.getDatefin().isAfter(challenge.getDatedebut()) && challenge.getDatedebut().isAfter(LocalDate.now())){
            List<Utilisateurs> listutilisateurs = new ArrayList<>();
            List<String> emails = new ArrayList<>();
            listutilisateurs = utilisateurRepository.findAll();
            for(Utilisateurs u : listutilisateurs){
                emails.add(u.getEmail());
            }
            String mon = "DevsCiwara vient de créer la challenge " + challenge.getTitre() + " en savoir plus  https://chat.openai.com/auth/login"
                    ;
            //envoyeMailService.sendEmailToMultipleRecipients("Nouvelle Challenge",mon,emails);


            this.challengeRepository.save(challenge);
            return Message.set("Challenge creer avec succès",true);
        }
        else{
           return Message.set("La date de fin ne doit être inferieur à la date de début et la date de début doit supperieur à " +
                   "la date d'ajourd'hui ",false);
        }

    }

    @Override
    public List<Challenge> afficher() {
        return challengeRepository.findAll();
    }

    @Override
    public Message modifier(Long id, Challenge challenge) {
        try {
            Challenge ch = challengeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Challenge no trouvée"));
            if (ch.getDatedebut().isBefore(LocalDate.now()))  {
                return Message.set("Désolé, cette challenge ne peut pas être modifiée car la date de début est antérieure ou égale à la date du jour",false);
            }
            ch.modifier(challenge.getTitre(), challenge.getCritere(),challenge.getDatedebut(),challenge.getDatefin());
            challengeRepository.save(ch);
            System.out.println(new Date());
            return Message.set("Challenge Modifié",true);
        } catch (RuntimeException e) {
            return Message.set("une erreur s'est produite",false);
        }
    }


    @Override
    public String supprimer(Long id) {
        this.challengeRepository.deleteById(id);
        return "Challenge supprime avec succes";
    }

    @Override
    public Challenge ChallengeParId(Long id) {
        return challengeRepository.findById(id).get();
    }
}
