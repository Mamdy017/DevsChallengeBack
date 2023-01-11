package DevsChallenge.example.DevsChallenge.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.DateTimeException;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String titre;
    private String critere;
    private String datedebut;
    private String datefin;

    @ManyToOne
    Categories categories;
    @ManyToOne
    Technologies technologies;


}
