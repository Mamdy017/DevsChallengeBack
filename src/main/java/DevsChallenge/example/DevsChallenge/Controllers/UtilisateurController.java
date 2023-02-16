package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Images.SaveImage;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Role;
import DevsChallenge.example.DevsChallenge.Models.Roles;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.Utilisateurservice;
import io.swagger.annotations.Api;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/afficher")
    public List<Utilisateurs> AfficherUsers(){
        log.info("Affichage de la liste des Collaborateurs ");

        return utilisateurservice.Afficher();
    }

    @GetMapping("/users")
    public List<Object[]> Afficher() {
        return utilisateurservice.userlist();

    }
    @GetMapping("/afficheruser")
    public List<Utilisateurs> AfficherUsersS() {
        List<Utilisateurs> listutilisateurs = new ArrayList<>();
        listutilisateurs = utilisateurRepository.findAll();
        List<Utilisateurs> listNonAdminUsers = new ArrayList<>();
        for (Utilisateurs u : listutilisateurs) {
            boolean isAdmin = false;
            for (Roles r : u.getRoles()) {
                if (r.getName().equals(Role.ROLE_ADMIN) || r.getName().equals(Role.adminuser)) {
                    isAdmin = true;
                    break;
                }
            }
            if (!isAdmin) {
                listNonAdminUsers.add(u);
            }
        }
        if(listNonAdminUsers.isEmpty()) {
            return null;
        }
        return listNonAdminUsers;
    }


    @PutMapping({"/modifier/{Id}"})
    public Object ModierUser(
            @PathVariable Long Id,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String prenom,
            @RequestParam String nom,
            @RequestParam String numero,
            @Param("profile") MultipartFile profile) throws IOException {
        String ProfileNom = username +profile.getOriginalFilename();

        Utilisateurs utilisateuramodifier = new Utilisateurs(username,email,nom,prenom,ProfileNom,numero);
        System.out.println(utilisateuramodifier);
        if (profile != null){

            utilisateuramodifier.setProfile(SaveImage.save(profile,ProfileNom,"profile"));
            log.info("Utilisateur "+utilisateuramodifier.getUsername() + " modifié avec succès");
            System.out.println(utilisateuramodifier);
            this.utilisateurservice.Modifier(Id,utilisateuramodifier);

        }
        else {
            Message.set("Ajouter un fichier valide",false);
        }
        return Message.set("Utilisateur modifié avec succès",true);
    }


    @PutMapping("/modifierMdp/{Id}")
    public Object ModierUserMdp(@PathVariable Long Id, @RequestParam String password) {
        Utilisateurs utilisateuramodifier = utilisateurservice.userParId(Id);
        utilisateuramodifier.setPassword(password);
        this.utilisateurservice.Modifier(Id,utilisateuramodifier);
        System.out.println(Id);
        return Message.set("Mot de passe modifié avec succès", true);
    }

    //::::::::::::::::::::::::::::::REINITIALISER PASSWORD::::::::::::::::::::::::::::::::::::::::::://

    @PostMapping("/resetPassword/{email}")
    public Message resetPassword(@PathVariable("email") String email) {
        Utilisateurs user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            Message message = new Message("Mail incorrect !",false);
            return message;
            // return new ResponseEntity<String>("Email non fourni", HttpStatus.BAD_REQUEST);
        }
        utilisateurservice.resetPassword(user);
        Message message = new Message("Un mail vous à été envoyer contenant un code de réinitialisation",true);
        return message;

        //return new ResponseEntity<String>("Email envoyé!", HttpStatus.OK);
    }


    //::::::::::::::::::::::::::::::::::::::::Changer mot de passe:::::::::::::::::::::::::::::::::::::::::::::::://

    @PostMapping("/changePassword")
    public Message changePassword(@RequestBody HashMap<String, String> request) {
        String emailOrNumero = request.get("emailOrNumero");
        Utilisateurs user = utilisateurRepository.findByUsername(emailOrNumero);

        if (user == null) {
            Message message = new Message("Utilisateur non fourni !",false);
            return message;
            //return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
        }
        String currentPassword = request.get("currentpassword");
        String newPassword = request.get("newpassword");
        String confirmpassword = request.get("confirmpassword");
        if (!newPassword.equals(confirmpassword)) {
            Message message = new Message("Mot de passe incorrect",false);
            return message;
            //return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
        }
        String userPassword = user.getPassword();
        try {
            if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
                if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
                    // System.out.println("Je suis vraiment okkkkkkkkkkkkkkkkkki");
                    utilisateurservice.updateUserPassword(user, newPassword);
                }
            } else {
                Message message = new Message("Code incorrect",false);
                return message;
                //return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
            }
            utilisateurservice.updateUserPassword(user, newPassword);
            Message message = new Message("Mot de passe changé avec succès !",true);
            return message;
            //return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
        } catch (Exception e) {
            Message message = new Message("Erreur",false);
            return message;
            //return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }





}
