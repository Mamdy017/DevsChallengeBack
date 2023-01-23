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
public class critere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String critere;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "critere_bareme",
            joinColumns = @JoinColumn(name = "critere_id"),
            inverseJoinColumns = @JoinColumn(name = "bareme_id"))
    private Set<bareme> bareme = new HashSet<>();
}
