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
    private String lienGithub;
    private String source;
    @Size(max=7)
    private String  point;
    @ManyToOne
    Challenge challenge;
    @ManyToOne
    Team team;

    public Solution(String lienGithub, String point, String photo2) {
        this.lienGithub=lienGithub;
        this.point=point;
        this.source=photo2;
    }
}
