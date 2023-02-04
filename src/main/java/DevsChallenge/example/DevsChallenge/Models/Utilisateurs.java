package DevsChallenge.example.DevsChallenge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "utilisateur",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Size(max = 2)
    private int mois;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    public Utilisateurs(String username, String email, String nom, String prenom, String password, String profile, int mois) {
        this.email=email;
        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
        this.username=username;
        this.profile = profile;
        this.mois=mois;
    }

    public Utilisateurs(Long userdid) {
        this.id = userdid;
    }

}
