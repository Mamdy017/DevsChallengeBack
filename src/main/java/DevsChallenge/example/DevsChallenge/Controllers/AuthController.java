package DevsChallenge.example.DevsChallenge.Controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import DevsChallenge.example.DevsChallenge.Configsecurite.JwtUtils;
import DevsChallenge.example.DevsChallenge.DTAO.Connexion;
import DevsChallenge.example.DevsChallenge.DTAO.Inscription;
import DevsChallenge.example.DevsChallenge.Messages.EmailConstructor;
import DevsChallenge.example.DevsChallenge.Messages.JwtResponse;
import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Role;
import DevsChallenge.example.DevsChallenge.Models.Roles;
import DevsChallenge.example.DevsChallenge.Models.Utilisateurs;
import DevsChallenge.example.DevsChallenge.Repositories.RolesRepostory;
import DevsChallenge.example.DevsChallenge.Repositories.UtilisateurRepository;
import DevsChallenge.example.DevsChallenge.Services.UserDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RolesRepostory rolesRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailConstructor emailConstructor;
    @Autowired
    JavaMailSender mailSender;
    @ApiOperation(value = "Connexion")
    @PostMapping("/connexion")
    public Object authenticateUser(@Valid @RequestBody Connexion connexion) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(connexion.getUsernameOrEmail(), connexion.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> {
                    return item.getAuthority();
                })
                .collect(Collectors.toList());

        if(userDetails.getEtat()){
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getNom(),
                    userDetails.getPrenom(),
                    userDetails.getProfile(),
                    userDetails.getNumero(),
                    userDetails.getMois(),
                    roles));
        }else {
            return  Message.set("Ce compte est bannis",false);
        }

    }
    @PostMapping("/connexion2")
    public Object authenticateUseradmin(@Valid @RequestBody Connexion connexion) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(connexion.getUsernameOrEmail(), connexion.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> {
                    return item.getAuthority();
                })
                .collect(Collectors.toList());

        if (roles.contains("ROLE_ADMIN") || roles.contains("adminuser")) {
            if(userDetails.getEtat()){
                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getNom(),
                        userDetails.getPrenom(),
                        userDetails.getProfile(),
                        userDetails.getNumero(),
                        userDetails.getMois(),
                        roles));
            }
            else {
                return  Message.set("Ce compte est bannis",false);
            }

        } else {
            return Message.set("Non d'utilisateur ou mot de passe incorrect",false);
        }
    }



    @ApiOperation(value = "Inscription")
    @PostMapping("/inscription")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Inscription inscription) {


        if (utilisateurRepository.existsByUsername(inscription.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new Message("Error: Ce nom d'utilisateur existe d??i??!",false));
        }

        if (utilisateurRepository.existsByEmail(inscription.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new Message("Error:Cet adress mail esxiste d??j??!",false));
        }

        // Create new user's account
        Utilisateurs user = new Utilisateurs(
                inscription.getUsername(),
                inscription.getEmail(),
                inscription.getNom(),
                inscription.getPrenom(),
                encoder.encode(inscription.getPassword()),
                inscription.getProfile(),
                inscription.getMois(),
                inscription.getNumero()

        );
        Set<String> strRoles = inscription.getRoles();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = rolesRepository.findByName(Role.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Erreur: Cet role n'existe pas",false))));

            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = rolesRepository.findByName(Role.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Erreur: Cet role n'existe pas",false))));
                        roles.add(adminRole);

                        break;
                    case "adminuser":
                        Roles modRole = rolesRepository.findByName(Role.adminuser)
                                .orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Erreur: Cet role n'existe pas",false))));
                        roles.add(modRole);

                        break;
                    default:
                        Roles userRole = rolesRepository.findByName(Role.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(String.valueOf(Message.set("Erreur: Cet role n'existe pas",false))));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setProfile("http://127.0.0.1/DesCiwara/Images/avatar.png");
        user.setEtat(true);
        user.setMois(LocalDate.now().getMonthValue());
        if (strRoles != null && strRoles.contains("adminuser")) {
            String password = String.valueOf(inscription.getPrenom().charAt(0)) + String.valueOf(inscription.getNom().charAt(0)) + inscription.getNom();
            password = password.toLowerCase();
            user.setPassword(encoder.encode(password));
            System.out.println(password);
        } else {
            user.setPassword(encoder.encode(inscription.getPassword()));
        }


        utilisateurRepository.save(user);
        //  mailSender.send(emailConstructor.constructNewUserEmail(user));
        System.out.println(mailSender);
        return ResponseEntity.ok(new Message("Utilisateur enregistr?? avec succes!",true));
    }

}
