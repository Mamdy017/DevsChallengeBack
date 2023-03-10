package DevsChallenge.example.DevsChallenge.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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

    @Column(length = 60000)

    private String description;

    private Date datedebut;
    private Date datefin;
    private String etat;
    private String photo;
    private  Boolean etat1;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "challenge_critere",
            joinColumns = @JoinColumn(name = "challenge_id"),
            inverseJoinColumns = @JoinColumn(name = "critere_id"))
    private Set<critere> critere = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "tecno_challenge",
            joinColumns = @JoinColumn(name = "challenge_id"),
            inverseJoinColumns = @JoinColumn(name = "techno_id"))
    private Set<Technologies> techno = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "cate_challenge",
            joinColumns = @JoinColumn(name = "challenge_id"),
            inverseJoinColumns = @JoinColumn(name = "cate_id"))
    private Set<Categories> cate = new HashSet<>();

    public Challenge(Long challengeid) {
        this.id=challengeid;
    }

    public Challenge(String titre,  String description, Date datedebut, Date datefin, String photo) {
        this.titre = titre;
        this.datedebut=datedebut;
        this.datefin=datefin;
        this.description=description;
        this.photo=photo;
    }

    public Challenge(String titre, String description, Date datedebut, Date datefin, String photo2, Long[] critereids, Long[] cateids, Long[] tecnhoids) {
    }


    public void modifier(String titre,  Date datedebut,Date datefin,String photo,String description) {
        this.titre = titre;

        this.datedebut=datedebut;
        this.datefin=datefin;
        this.photo=photo;
        this.description=description;
    }

    public void updateChallengeStatus() {
        Date now = new Date();
        if (datedebut.after(now)) {
            etat = "Avenir";
        } else if (datedebut.before(now) && datefin.after(now)) {
            etat = "Encours";
        } else if (datefin.before(now)) {
            etat = "Termin??";
        }
    }

}
