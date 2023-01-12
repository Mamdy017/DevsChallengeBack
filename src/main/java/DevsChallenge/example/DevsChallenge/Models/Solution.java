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
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max=20)
    private String nom;
    private String lienGithub;
    private String source;
    @Size(max=7)
    private String  point;
    @ManyToOne
    Utilisateurs utilisateurs;
    @ManyToOne
    Challenge challenge;
}
