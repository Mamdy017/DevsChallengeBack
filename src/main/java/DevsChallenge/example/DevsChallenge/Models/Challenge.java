package DevsChallenge.example.DevsChallenge.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    private String critere1;
    private String critere2;
    private String critere3;
    private String critere4;
    private String description;

    private Date datedebut;
    private Date datefin;
    private String photo;
    @ManyToOne
    Categories categories;
    @ManyToOne
    Technologies technologies;


    public Challenge(Long challengeid) {
        this.id=challengeid;
    }

    public Challenge(String titre, String critere1, String critere2, String critere3, String critere4, String description, Date datedebut, Date datefin, String photo) {
        this.titre = titre;
        this.critere1 = critere1;
        this.critere2=critere2;
        this.critere3=critere3;
        this.critere4=critere4;
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.description=description;
        this.photo=photo;
    }


    public void modifier(String titre, String critere1,String critere2,String critere3,String critere4, Date datedebut,Date datefin,String photo,String description) {
        this.titre = titre;
        this.critere1 = critere1;
        this.critere2=critere2;
        this.critere3=critere3;
        this.critere4=critere4;
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.photo=photo;
        this.description=description;
    }

}
