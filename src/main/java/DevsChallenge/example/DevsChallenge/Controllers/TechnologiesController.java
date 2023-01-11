package DevsChallenge.example.DevsChallenge.Controllers;

import DevsChallenge.example.DevsChallenge.Messages.Message;
import DevsChallenge.example.DevsChallenge.Models.Technologies;
import DevsChallenge.example.DevsChallenge.Services.TechnoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "devsCiwara", description = "")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/devs/auth/techo")
@RestController
@ToString
public class TechnologiesController {

    @Autowired
    TechnoService technoService;

    @ApiOperation(value = "Ajouter2 un pays")
    @PostMapping("/ajout")
    public Object creer (@RequestBody Technologies technologies){

        try {
            return technoService.creer(technologies);
        } catch (Exception e) {
            return Message.ErreurReponse(" la techno " + technologies.getNom() + " existe déjà", HttpStatus.OK, null);
        }
    }

    @ApiOperation(value = "Afficher techonologies")
    @GetMapping("/afficher")
    public List<Technologies> list(){
        return technoService.afficher();
    }

    @ApiOperation(value = "Modifier technologies")
    @PutMapping("/modifier/{Id}")
    public Technologies Modifier(@PathVariable Long Id, Technologies technologies) {
        return technoService.modifier(Id, technologies);
    }

    @ApiOperation(value = "Supprimer Technologies")
    @DeleteMapping("/Supprimer/{Id}")
    public String Supprimer(@PathVariable Long Id) {

        return technoService.supprimer(Id);
    }



}

