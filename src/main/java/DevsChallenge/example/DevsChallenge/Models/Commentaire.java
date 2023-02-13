package DevsChallenge.example.DevsChallenge.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String description;
    private int likes;
    private int dislike;
    @ManyToOne
    Question question;
    @ManyToOne
    Utilisateurs utilisateurs;


    public void incrementType1() {
        this.likes++;
    }

    public void incrementType2() {
        this.dislike++;
    }
}
