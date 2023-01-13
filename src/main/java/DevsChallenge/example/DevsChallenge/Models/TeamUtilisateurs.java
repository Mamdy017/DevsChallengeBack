package DevsChallenge.example.DevsChallenge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class TeamUtilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type;
    @ManyToOne
    Utilisateurs utilisateurs;
    @ManyToOne
    Challenge challenge;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "team_teamusers",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "teamusers_id"))
    private Set<Team> team = new HashSet<>();
}
