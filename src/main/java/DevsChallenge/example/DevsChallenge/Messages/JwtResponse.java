package DevsChallenge.example.DevsChallenge.Messages;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String nom;
    private String prenom;
    private String profile;
    private String numero;
    private int mois;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email,String nom, String prenom, String profile, String numero,int mois,  List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom=nom;
        this.prenom=prenom;
        this.profile=profile;
        this.roles = roles;
        this.numero=numero;
        this.mois=mois;
    }
    public JwtResponse(String accessToken,  String username, String email) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
