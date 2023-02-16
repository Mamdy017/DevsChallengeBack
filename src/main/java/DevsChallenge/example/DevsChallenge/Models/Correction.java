package DevsChallenge.example.DevsChallenge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table
public class Correction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String etat;

    @ManyToOne
    Solution solution;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "correction_critere",
            joinColumns = @JoinColumn(name = "correction_id"),
            inverseJoinColumns = @JoinColumn(name = "critere_id"))
    private Set<critere> critere = new HashSet<>();



}
