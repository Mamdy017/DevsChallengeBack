package DevsChallenge.example.DevsChallenge.DTAO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class Inscription {

    @Size(max = 20)
    private String username;
    @Size(max = 50)
    @Email
    private String email;
    @Size(max=50)
    private String nom;
    @Size(max=50)
    private String prenom;
    @Size(max=150)
    private String password;
    private String profile;
    @Size(max=150)
    private String numero;
    @Size(max = 2)
    private int mois;
    private Set<String> roles;
}
