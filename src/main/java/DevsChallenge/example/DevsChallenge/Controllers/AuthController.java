package DevsChallenge.example.DevsChallenge.Controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import DevsChallenge.example.DevsChallenge.Configsecurite.JwtUtils;
import DevsChallenge.example.DevsChallenge.DTAO.Connexion;
import DevsChallenge.example.DevsChallenge.DTAO.Inscription;
import DevsChallenge.example.DevsChallenge.Messages.JwtResponse;
import DevsChallenge.example.DevsChallenge.Messages.MessageResponse;
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

    @ApiOperation(value = "Connexion")
    @PostMapping("/connexion")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Connexion connexion) {

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

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }



    @ApiOperation(value = "Inscription")
    @PostMapping("/inscription")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Inscription inscription) {
        if (utilisateurRepository.existsByUsername(inscription.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (utilisateurRepository.existsByEmail(inscription.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Utilisateurs user = new Utilisateurs(
                inscription.getUsername(),
                inscription.getEmail(),
                inscription.getNom(),
                inscription.getPrenom(),
                encoder.encode(inscription.getPassword()),
                inscription.getProfile());

        Set<String> strRoles = inscription.getRoles();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = rolesRepository.findByName(Role.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erreur: Cet role n'existe pas."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = rolesRepository.findByName(Role.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur: Cet role n'existe pas."));
                        roles.add(adminRole);

                        break;
                    case "recruteur":
                        Roles modRole = rolesRepository.findByName(Role.recruteur)
                                .orElseThrow(() -> new RuntimeException("Erreur: Cet role n'existe pas."));
                        roles.add(modRole);

                        break;
                    default:
                        Roles userRole = rolesRepository.findByName(Role.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur: Cet role n'existe pas."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        utilisateurRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succes!"));
    }

}
