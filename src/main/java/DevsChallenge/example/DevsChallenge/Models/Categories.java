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
@Table
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20)
    private String nom;

    public Categories(String cate) {
        this.nom=cate;
    }

    public void modifier(String nom) {
        this.nom = nom;

    }
}
