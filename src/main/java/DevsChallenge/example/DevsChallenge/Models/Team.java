package DevsChallenge.example.DevsChallenge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "challenge_team",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "challenge_id") }
    )
    private List<Challenge> challenges;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "utilisateur_team",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "utilisateur_id") }
    )
    private List<Utilisateurs> utilisateurs;
    // getters and setters
}
