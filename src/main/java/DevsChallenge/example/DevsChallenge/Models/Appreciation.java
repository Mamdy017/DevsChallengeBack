package DevsChallenge.example.DevsChallenge.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Appreciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type;
    private int type2;
    @ManyToOne
    Commentaire commentaire;
    @ManyToOne
    Utilisateurs utilisateurs;

    public void incrementType1() {
        this.type++;
    }

    public void incrementType2() {
        this.type2++;
    }

}
