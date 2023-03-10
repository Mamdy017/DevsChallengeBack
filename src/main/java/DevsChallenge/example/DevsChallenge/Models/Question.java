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
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ManyToOne
    Challenge challenge;
    @ManyToOne
    Utilisateurs utilisateurs;



    public Question(Question question) {
        this.question= String.valueOf(question);
    }

    public Question(Long questionid) {
        this.id=questionid;
    }
}
