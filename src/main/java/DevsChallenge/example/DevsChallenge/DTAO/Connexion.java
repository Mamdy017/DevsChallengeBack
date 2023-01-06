package DevsChallenge.example.DevsChallenge.DTAO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Connexion {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
